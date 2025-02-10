package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.request.RequestRegisterUserDTO;
import vn.duongvct.test.epl_app.domain.request.RequestUpdateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUpdateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.service.UserService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @ApiMessage("Create a new user")
    public ResponseEntity<ResponseCreateUserDTO> createNewUser(@Valid @RequestBody RequestRegisterUserDTO postManUser)
            throws InvalidRequestException {

        boolean isEmailExit = this.userService.isEmailExists(postManUser.getEmail());
        if (isEmailExit) {
            throw new InvalidRequestException("Email" + postManUser.getEmail() + " đã tồn tại, vui lòng sử dụng email khác");
        }
        // Mã hóa password
        String hashPassword = this.passwordEncoder.encode(postManUser.getPassword());
        // ghi đè lại password
        postManUser.setPassword(hashPassword);
        // save database
        User user = this.userService.handleSaveUser(this.userService.convertRequestRegisterDTOtoUser(postManUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertUserToResCreateUserDTO(user));
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage("Delete a user")

    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws InvalidRequestException {
        Optional<User> currentUser = this.userService.getUserById(id);
        if (!currentUser.isPresent()) {
            throw new InvalidRequestException("User với id = " + id + " không tồn tại");
        }

        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
        // return ResponseEntity.status(HttpStatus.OK).body("delete user");
        // status 204
        // return ResponseEntity.noContent().build();
    }

    // fetch user by id
    @GetMapping("/users/{id}")
    @ApiMessage("fetch user by id")

    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable("id") long id) throws InvalidRequestException {
        Optional<User> currentUser = this.userService.getUserById(id);
        if (!currentUser.isPresent()) {
            throw new InvalidRequestException("User với id = " + id + " không tồn tại");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertUserToResponseUserDTO(currentUser.get()));
    }

    // fetch all users
    @GetMapping("/users")
    @ApiMessage("fetch all users")
    public ResponseEntity<ResultPaginationDTO> fetchAllUser(
            @Filter Specification<User> spec,
            Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUsers(spec, pageable));
    }

    // update a user
    @PutMapping("/users")
    @ApiMessage("Update a user")

    public ResponseEntity<ResponseUpdateUserDTO> updateUser(@RequestBody RequestUpdateUserDTO postManUser) throws InvalidRequestException {

        Optional<User> currentUser = this.userService.getUserById(postManUser.getId());

        if (!currentUser.isPresent()) {
            throw new InvalidRequestException("User với id = " + postManUser.getId() + " không tồn tại");
        }
        User editedUser = this.userService.handleUpdateUser(currentUser.get(), postManUser);

        // if (user == null) {
        // throw new IdInvalidException("User với id = " + user.getId() + " không tồn
        // tại ");
        // }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertUserToResponseUpdateUserDTO(editedUser));
    }

}

package vn.duongvct.test.epl_app.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResponseCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUpdateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
import vn.duongvct.test.epl_app.service.UserService;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/users")
    @ApiMessage("Create a new user")
    public ResponseEntity<ResponseCreateUserDTO> createNewUser(@Valid @RequestBody User user) throws InvalidRequestException {
        if (this.userService.isEmailExists(user.getEmail())) {
            throw new InvalidRequestException("Email " + user.getEmail() + " has already exists, please choose another email!");
        }
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User createdUser = this.userService.handleSaveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertUserToResCreateUserDTO(createdUser));
    }

    @GetMapping("/users/{id}")
    @ApiMessage("Fetch user by id")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id) throws InvalidRequestException {
        Optional<User> user = this.userService.getUserById(id);
        if(!user.isPresent()) {
            throw new InvalidRequestException("Can not found user with id = " + id);

        }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertUserToResponseUserDTO(user.get()));
    }

    @DeleteMapping("/user/{id}")
    @ApiMessage("Delete user by id")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws InvalidRequestException {
        Optional<User> user = this.userService.getUserById(id);
        if (!user.isPresent()) {
            throw new InvalidRequestException("Cannot find user with id = " + id);
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/users")
    @ApiMessage("Update a user")
    public ResponseEntity<ResponseUpdateUserDTO> updateUser(@Valid @RequestBody User user) throws InvalidRequestException {
        User editedUser = this.userService.getUserById(user.getId()).get();
        if (editedUser == null) {
            throw new InvalidRequestException("Cannot find user with id = " + user.getId());
        }
        editedUser = this.userService.handleSaveUser(editedUser);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.convertUserToResponseUpdateUserDTO(editedUser));
    }

    @GetMapping("/users")
    @ApiMessage("Fetch all users with pagination")
    public ResponseEntity<ResultPaginationDTO> fetchAllUsers(
        @Filter Specification<User> spec,
        Pageable pageable
    )  {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.fetchAllUsers(spec, pageable));
    }




}

package vn.duongvct.test.epl_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResLoginDTO;
import vn.duongvct.test.epl_app.service.UserService;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ResCreateUserDTO> register(@Valid @RequestBody User user) throws InvalidRequestException{
        if (this.userService.isEmailExists(user.getEmail())) {
            throw new InvalidRequestException("Email " + user.getEmail() + " is already exists. Please choose another email");
        }
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User createdUser = this.userService.handleSaveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertUserToResCreateUserDTO(createdUser));

    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResLoginDTO> login( @)



}

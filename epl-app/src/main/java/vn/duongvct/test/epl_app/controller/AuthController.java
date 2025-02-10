package vn.duongvct.test.epl_app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.request.RequestLoginDTO;
import vn.duongvct.test.epl_app.domain.request.RequestRegisterUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseLoginDTO;
import vn.duongvct.test.epl_app.service.UserService;
import vn.duongvct.test.epl_app.util.SecurityUtil;
import vn.duongvct.test.epl_app.util.annotation.ApiMessage;
import vn.duongvct.test.epl_app.util.exception.InvalidRequestException;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private SecurityUtil securityUtil;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ResponseCreateUserDTO> register(@Valid @RequestBody RequestRegisterUserDTO requestRegisterUserDTO) throws InvalidRequestException{
        if (this.userService.isEmailExists(requestRegisterUserDTO.getEmail())) {
            throw new InvalidRequestException("Email " + requestRegisterUserDTO.getEmail() + " is already exists. Please choose another email");
        }
        String hashedPassword = this.passwordEncoder.encode(requestRegisterUserDTO.getPassword());
        requestRegisterUserDTO.setPassword(hashedPassword);
        User createdUser = this.userService.handleSaveUser(this.userService.convertRequestRegisterDTOtoUser(requestRegisterUserDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertUserToResCreateUserDTO(createdUser));

    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody RequestLoginDTO requestLoginDTO) {
        //take input(username and password) into security
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(requestLoginDTO.getUsername(), requestLoginDTO.getPassword());

        //authenticate user (override UserDetailsService bean)
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);

        //push information if success to securitycontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //not pass password
        ResponseLoginDTO resLoginDTO = new ResponseLoginDTO();
        User currentUser = this.userService.getUserByUsername(requestLoginDTO.getUsername());
        if (currentUser != null) {
            ResponseLoginDTO.UserLogin userLogin = new ResponseLoginDTO.UserLogin(
                currentUser.getId(),
                currentUser.getEmail(),
                currentUser.getName()
            );
            resLoginDTO.setUser(userLogin);
        }
        String accessToken = this.securityUtil.createAccessToken(requestLoginDTO.getUsername(), resLoginDTO);
        resLoginDTO.setAccessToken(accessToken);

        String refreshToken = this.securityUtil.createRefreshToken(requestLoginDTO.getUsername(), resLoginDTO);

        this.userService.updateUserToken(refreshToken, requestLoginDTO.getUsername());

        //set cookies
        ResponseCookie resCookies = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                // .domain("example.com")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookies.toString())
                .body(resLoginDTO);

    }
    @GetMapping("/auth/account")
    @ApiMessage("fetch account")
    public ResponseEntity<ResponseLoginDTO.UserGetAccount> getAccount() {

        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        // Lấy user
        User currentUserDB = this.userService.getUserByUsername(email);
        ResponseLoginDTO.UserLogin userLogin = new ResponseLoginDTO.UserLogin();
        ResponseLoginDTO.UserGetAccount userGetAccount = new ResponseLoginDTO.UserGetAccount();

        if (currentUserDB != null) {
            userLogin.setId(currentUserDB.getId());
            userLogin.setEmail(currentUserDB.getEmail());
            userLogin.setName(currentUserDB.getName());
            // userLogin.setRole(currentUserDB.getRole());

            userGetAccount.setUser(userLogin);
        }

        return ResponseEntity.ok().body(userGetAccount);
    }
    @GetMapping("/auth/refresh")
    @ApiMessage("Get User by refresh token")

    public ResponseEntity<ResponseLoginDTO> getRefreshToken(
            @CookieValue(name = "refresh_token", defaultValue = "abc") String refreshToken) throws InvalidRequestException {
        if (refreshToken.equals("abc")) {
            throw new InvalidRequestException("Bạn không có refresh token ở cookie");
        }

        // check valid
        Jwt decodedToken = this.securityUtil.checkValidRefreshToken(refreshToken);
        String email = decodedToken.getSubject();

        // check user by token + email
        User currentUser = this.userService.getUserByRefreshTokenAndEmail(refreshToken, email);
        if (currentUser == null) {
            throw new InvalidRequestException("Refresh Token không hợp lệ");
        }

        // issue new token/set refresh token as cookies
        ResponseLoginDTO res = new ResponseLoginDTO();
        User currentUserDB = this.userService.getUserByUsername(email);
        if (currentUserDB != null) {

            ResponseLoginDTO.UserLogin userLogin = new ResponseLoginDTO.UserLogin(
                    currentUserDB.getId(),
                    currentUserDB.getEmail(),
                    currentUserDB.getName()
                    // currentUserDB.getRole()
            );
            res.setUser(userLogin);
        }

        // create access_token token
        String accessToken = this.securityUtil.createAccessToken(email, res);

        res.setAccessToken(accessToken);

        // create refresh token
        String newRefreshToken = this.securityUtil.createRefreshToken(email, res);

        // Update user with new_refresh_token
        this.userService.updateUserToken(newRefreshToken, email);

        // set cookies
        ResponseCookie resCookies = ResponseCookie.from("refresh_token", newRefreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                // .domain("example.com")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookies.toString())
                .body(res);
    }

    @PostMapping("/auth/logout")
    @ApiMessage("Logout User")
    public ResponseEntity<Void> logout() throws InvalidRequestException {
        // Lấy email từ Spring security
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";

        if (email.equals("email")) {
            throw new InvalidRequestException("Access Token is not valid");
        }

        // Update refresh token = null
        this.userService.updateUserToken(null, email);

        // remove refresh token cookie
        ResponseCookie deleteSpringCookie = ResponseCookie
                .from("refresh_token", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)

                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString())
                .body(null);
    }



}

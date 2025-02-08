package vn.duongvct.test.epl_app.config;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.service.UserService;

@Component("userDetailsService")
public class CustomUserDetails implements UserDetailsService{
    private final UserService userService;

    public CustomUserDetails(UserService userService) {
        this.userService = userService;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username/password is not valid");
        }
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
    

}

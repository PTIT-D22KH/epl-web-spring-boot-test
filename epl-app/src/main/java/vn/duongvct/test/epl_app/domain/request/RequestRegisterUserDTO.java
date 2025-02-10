package vn.duongvct.test.epl_app.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterUserDTO {
    
    private String name;
    @NotBlank(message = "Please enter your email")
    private String email;
    @NotBlank(message = "Please enter your password")
    private String password;

}

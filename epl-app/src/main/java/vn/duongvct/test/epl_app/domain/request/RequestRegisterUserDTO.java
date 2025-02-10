package vn.duongvct.test.epl_app.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterUserDTO {
    
    @NotBlank(message = "Please enter your email")
    private String email;
    private String name;
    @NotBlank(message = "Please enter your password")
    private String password;

}

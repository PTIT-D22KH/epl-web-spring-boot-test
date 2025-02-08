package vn.duongvct.test.epl_app.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResLoginDTO {
    @JsonProperty("access_token")
    private String accessToken;

    private UserLogin user;

    @Getter
    @Setter
    public static class UserLogin {
        private Long id;
        private String email;
        private String name;
        
    }


}

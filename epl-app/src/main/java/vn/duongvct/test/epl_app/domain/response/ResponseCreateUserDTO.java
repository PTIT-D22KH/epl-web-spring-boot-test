package vn.duongvct.test.epl_app.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateUserDTO {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;
    

}

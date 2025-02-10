package vn.duongvct.test.epl_app.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUpdateUserDTO {
    private Long id;
    private String name;
    private String email;
    private Instant updatedAt;
    private String updatedBy;

}

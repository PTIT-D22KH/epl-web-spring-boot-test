package vn.duongvct.test.epl_app.domain.response;

import java.time.Instant;

import lombok.Setter;
import lombok.Getter;
import vn.duongvct.test.epl_app.constant.GenderEnum;


@Getter
@Setter
public class ResponseUserDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String nation;
    private int age;
    private GenderEnum gender;
    private Instant createdAt;
    private Instant updatedAt;

    

}

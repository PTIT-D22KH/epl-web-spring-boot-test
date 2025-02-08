package vn.duongvct.test.epl_app.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;

@Getter
@Setter
public class ResCreateUserDTO {
    private Long id;
    private String name;
    private String email;
    private GenderEnum gender;
    private int age;
    private String address;
    private String nation;
    private Instant createdAt;
    

}

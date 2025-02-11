package vn.duongvct.test.epl_app.domain.response.coach;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;

@Getter
@Setter
public class ResponseCreateCoachDTO {
    private Long id;
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private Instant createdAt;
}

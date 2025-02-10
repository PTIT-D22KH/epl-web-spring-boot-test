package vn.duongvct.test.epl_app.domain.response.player;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;

@Setter
@Getter
public class ResponseCreatePlayerDTO {
    private Long id;
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private Instant createdAt;

}

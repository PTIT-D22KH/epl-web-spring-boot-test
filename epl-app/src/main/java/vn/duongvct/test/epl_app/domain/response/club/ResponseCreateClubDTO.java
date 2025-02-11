package vn.duongvct.test.epl_app.domain.response.club;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateClubDTO {
    private Long id;
    private String name;
    private String nation;
    private Instant createdAt;

}

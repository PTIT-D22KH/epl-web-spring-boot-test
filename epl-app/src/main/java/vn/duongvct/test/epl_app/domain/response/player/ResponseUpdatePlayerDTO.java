package vn.duongvct.test.epl_app.domain.response.player;

import java.time.Instant;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;
import vn.duongvct.test.epl_app.domain.request.player.RequestClubHistoryInPlayerDTO;
import vn.duongvct.test.epl_app.domain.request.player.RequestPlayerPositionInPlayerDTO;

@Getter
@Setter
public class ResponseUpdatePlayerDTO {
    private Long id;
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private Instant updatedAt;
}

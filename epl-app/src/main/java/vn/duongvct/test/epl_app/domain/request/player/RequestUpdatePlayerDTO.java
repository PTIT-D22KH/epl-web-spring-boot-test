package vn.duongvct.test.epl_app.domain.request.player;

import java.time.Instant;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;
@Getter
@Setter
public class RequestUpdatePlayerDTO {
    private Long id;
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private List<RequestPlayerPositionInPlayerDTO> positions;
    private List<RequestClubPlayerInPlayerDTO> clubHistory;
}

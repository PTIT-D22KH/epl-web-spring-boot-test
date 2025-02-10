package vn.duongvct.test.epl_app.domain.request.player;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;
import vn.duongvct.test.epl_app.domain.ClubPlayer;
import vn.duongvct.test.epl_app.domain.PlayerPosition;

@Getter
@Setter
public class RequestCreatePlayerDTO {
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private List<PlayerPosition> positions;
    private List<ClubPlayer> clubHistory;
}

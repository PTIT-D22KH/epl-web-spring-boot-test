package vn.duongvct.test.epl_app.domain.request.club;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.domain.ClubCoach;
import vn.duongvct.test.epl_app.domain.ClubPlayer;

@Getter
@Setter
public class RequestUpdateClubDTO {
    private Long id;
    private String name;
    private String nation;
    private List<ClubPlayer> playerHistory;
    private List<ClubCoach> coachHistory;
}

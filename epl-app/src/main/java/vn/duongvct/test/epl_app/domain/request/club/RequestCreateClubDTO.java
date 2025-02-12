package vn.duongvct.test.epl_app.domain.request.club;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestCreateClubDTO {
    private String name;
    private String nation;
    private List<RequestClubPlayerInClubDTO> playerHistory;
    private List<RequestClubCoachInClubDTO> coachHistory;
}

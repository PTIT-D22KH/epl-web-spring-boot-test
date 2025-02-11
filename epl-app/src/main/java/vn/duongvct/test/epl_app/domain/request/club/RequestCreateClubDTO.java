package vn.duongvct.test.epl_app.domain.request.club;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.domain.ClubCoach;
import vn.duongvct.test.epl_app.domain.ClubPlayer;
@Getter
@Setter
public class RequestCreateClubDTO {
    private String name;
    private String nation;
    private List<ClubPlayer> playerHistory;
    private List<ClubCoach> coachHistory;
}

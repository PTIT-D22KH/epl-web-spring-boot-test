package vn.duongvct.test.epl_app.domain.request.player;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreatePlayerDTO {
    
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private List<RequestPlayerPositionInPlayerDTO> positions;
    private List<RequestClubPlayerInPlayerDTO> clubHistory;
}

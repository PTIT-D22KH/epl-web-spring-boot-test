package vn.duongvct.test.epl_app.domain.request.coach;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;
@Getter
@Setter
public class RequestUpdateCoachDTO {
    private Long id;
    private String name;
    private String nation;
    private int age;
    private GenderEnum gender;
    private List<RequestClubCoachInCoachDTO> clubHistory;

}

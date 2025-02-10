package vn.duongvct.test.epl_app.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateUserDTO {
    private Long id;
    private String email;
    private String name;
}

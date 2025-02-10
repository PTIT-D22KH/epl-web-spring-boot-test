package vn.duongvct.test.epl_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class TestDTO {
    private String name;
    private String email;
    private String password;
}


@RestController
@RequestMapping("/api/v1/test/hello")
public class HelloController {
    @PostMapping("/test-post")
    public void test(@RequestBody TestDTO testDTO) {
        System.out.println(testDTO);
    }

}

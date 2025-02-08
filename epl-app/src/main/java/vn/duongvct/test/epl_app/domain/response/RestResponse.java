package vn.duongvct.test.epl_app.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
    private int statusCode;
    private String error;
    private Object message;//message can be object or list
    private T data;


}

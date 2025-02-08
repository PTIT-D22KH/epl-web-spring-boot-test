package vn.duongvct.test.epl_app.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.duongvct.test.epl_app.domain.response.RestResponse;

@RestControllerAdvice
public class GlobalException {
    //handle all exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleAllException(Exception exception) {
        RestResponse<Object> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError("Internal Server Error");
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {
        InvalidRequestException.class
    } )
    public ResponseEntity<RestResponse<Object>> handleInvalidRequestException(InvalidRequestException exception) {
        RestResponse<Object> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setError("Exception occurs");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}

package in.yash.hotel.service.ExceptionHandler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiErrorResponse {

    private HttpStatus httpStatus;
    private String message;

}

package in.yash.user.service.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>handleResouceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ApiResponse response=ApiResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(response,
                HttpStatus.NOT_FOUND);
    }

}

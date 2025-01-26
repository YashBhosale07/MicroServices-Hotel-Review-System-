package in.yash.user.service.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {
    private HttpStatus httpStatus;
    private String message;
}

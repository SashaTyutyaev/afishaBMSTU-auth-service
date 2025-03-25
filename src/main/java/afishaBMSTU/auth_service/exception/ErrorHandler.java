package afishaBMSTU.auth_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler({IncorrectTokenException.class, IncorrectParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(IncorrectTokenException e) {
        log.error("Handle IncorrectParameterException: {}", e.getMessage());
        return new ErrorResponse("Incorrect parameter", HttpStatus.BAD_REQUEST,
                e.getMessage(), LocalDateTime.now().format(FORMATTER));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        log.error("Handle NotFoundException: {}", e.getMessage());
        return new ErrorResponse("Not found", HttpStatus.NOT_FOUND,
                e.getMessage(), LocalDateTime.now().format(FORMATTER));
    }
}

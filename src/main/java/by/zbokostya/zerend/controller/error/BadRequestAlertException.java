package by.zbokostya.zerend.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestAlertException extends RuntimeException {
    public BadRequestAlertException() {
        super();
    }
    public BadRequestAlertException(String message, Throwable cause) {
        super(message, cause);
    }
    public BadRequestAlertException(String message) {
        super(message);
    }
    public BadRequestAlertException(Throwable cause) {
        super(cause);
    }

}

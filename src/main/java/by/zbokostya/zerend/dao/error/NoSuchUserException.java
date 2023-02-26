package by.zbokostya.zerend.dao.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException() {
        super();
    }
    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoSuchUserException(String message) {
        super(message);
    }
    public NoSuchUserException(Throwable cause) {
        super(cause);
    }

}

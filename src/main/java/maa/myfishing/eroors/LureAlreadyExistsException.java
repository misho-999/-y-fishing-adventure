package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Lure is exists.")
public class LureAlreadyExistsException extends RuntimeException {
    private int statusCode;

    public LureAlreadyExistsException() {
        this.statusCode = 409;
    }

    public LureAlreadyExistsException(String message) {
        super(message);
        this.statusCode = 409;
    }

    public int getStatusCode() {
        return statusCode;
    }

}

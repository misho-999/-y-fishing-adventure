package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Town is exists.")
public class TownAlreadyExistException extends RuntimeException {
    private int statusCode;

    public TownAlreadyExistException() {
        this.statusCode = 409;
    }

    public TownAlreadyExistException(String message) {
        super(message);
        this.statusCode = 409;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Fishing is exists.")
public class FishingAlreadyExistsException extends RuntimeException {
    private int statusCode;

    public FishingAlreadyExistsException() {
        this.statusCode = 409;
    }

    public FishingAlreadyExistsException(String message) {
        super(message);
        this.statusCode = 409;
    }

    public int getStatusCode() {
        return statusCode;
    }

}

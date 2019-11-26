package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Fishing not found!")
public class FishingNotFoundException extends RuntimeException {
    private int statusCode;

    public FishingNotFoundException() {
        this.statusCode = 404;
    }

    public FishingNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

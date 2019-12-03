package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Fish not found!")
public class FishNotFoundException extends RuntimeException {
    private int statusCode;

    public FishNotFoundException() {
        this.statusCode = 404;
    }

    public FishNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

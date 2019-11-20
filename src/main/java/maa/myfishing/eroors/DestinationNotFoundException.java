package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Destination not found!")
public class DestinationNotFoundException extends RuntimeException {
    private int statusCode;

    public DestinationNotFoundException() {
        this.statusCode = 404;
    }

    public DestinationNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

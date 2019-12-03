package maa.myfishing.eroors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Lure not found!")
public class LureNotFoundException extends RuntimeException {
    private int statusCode;

    public LureNotFoundException() {
        this.statusCode = 404;
    }

    public LureNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

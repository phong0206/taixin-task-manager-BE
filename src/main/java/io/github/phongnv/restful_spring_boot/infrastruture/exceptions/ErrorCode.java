package io.github.phongnv.restful_spring_boot.infrastruture.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BadRequest(400, "Bad request", HttpStatus.BAD_REQUEST),
    InternalServerError(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    Forbidden(403, "Forbidden", HttpStatus.FORBIDDEN),
    Unauthorized(401, "Unauthorized", HttpStatus.UNAUTHORIZED),
    NotFound(404, "Not found", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

package io.github.phongnv.restful_spring_boot.infrastruture.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;

    }
}
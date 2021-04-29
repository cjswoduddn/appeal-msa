package icu.appeal.commonlibrary.dto.response;


import icu.appeal.commonlibrary.code.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final int status;
    private final String code;

//    private List<FieldError> errors;


    private ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getStatus(), errorCode.getCode());
    }
}
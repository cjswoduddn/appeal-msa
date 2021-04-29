package icu.appeal.commonlibrary.dto.response;

import icu.appeal.commonlibrary.code.SuccessCode;

public class SuccessResponse<D> {
    private String message;
    private int status;
    private String code;
    private D data;

    private SuccessResponse(String message, int status, String code, D data) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public static<D> SuccessResponse of(SuccessCode code, D data) {
        return new SuccessResponse(code.getMessage(), code.getStatus(), code.getCode(), data);
    }
}

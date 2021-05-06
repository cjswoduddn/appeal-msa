package icu.appeal.commonlibrary.exception;

import icu.appeal.commonlibrary.code.ErrorCode;

public class FailUploadImageException extends BusinessException{
    public FailUploadImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}

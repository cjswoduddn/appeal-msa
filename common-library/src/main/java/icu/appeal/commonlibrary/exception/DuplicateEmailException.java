package icu.appeal.commonlibrary.exception;

import icu.appeal.commonlibrary.code.ErrorCode;

public class DuplicateEmailException extends BusinessException{
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}

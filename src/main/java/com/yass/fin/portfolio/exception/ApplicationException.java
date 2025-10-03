package com.yass.fin.portfolio.exception;

import com.yass.fin.portfolio.enums.ErrorCodes;

import java.util.Map;

public class ApplicationException extends Exception {
    private ErrorCodes errorCode;
    private Map<String, Object> metaData;

    public ApplicationException(Throwable e, String message) {
        super(message, e);
    }

    public ApplicationException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(String message, ErrorCodes errorCode, Map<String, Object> metaData) {
        super(message);
        this.errorCode = errorCode;
        this.metaData = metaData;
    }

    public Map<String, Object> getMetaData() {
        return this.metaData;
    }

    public ErrorCodes getErrorCode() {
        return this.errorCode;
    }

}

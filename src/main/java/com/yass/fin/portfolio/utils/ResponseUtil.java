package com.yass.fin.portfolio.utils;

import com.yass.fin.portfolio.dto.ResponseDto;
import com.yass.fin.portfolio.enums.ErrorCodes;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    public static <T> ResponseDto<T> success(T data) {
        return ResponseDto.<T>builder()
                .statusCode(200)
                .statusMessage("Request Successful")
                .status(AppConstants.SUCCESS)
                .data(data)
                .build();
    }

    public static <T> ResponseDto<T> error(ErrorCodes errorCodes, String msg, HttpStatus status) {
        return ResponseDto.<T>builder()
                .statusCode(status.value())
                .statusMessage(msg)
                .status(AppConstants.ERROR)
                .errorCode(errorCodes)
                .build();
    }

    public static <T> ResponseDto<T> error(int errorCode, String msg) {
    	return ResponseDto.<T>builder()
    			.statusCode(errorCode)
    			.statusMessage(msg)
    			.status(AppConstants.ERROR)
    			.errorCode(ErrorCodes.SRV500)
    			.build();
    }
}

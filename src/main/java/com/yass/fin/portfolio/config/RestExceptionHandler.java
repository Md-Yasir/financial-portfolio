package com.yass.fin.portfolio.config;

import com.yass.fin.portfolio.dto.ResponseDto;
import com.yass.fin.portfolio.enums.ErrorCodes;
import com.yass.fin.portfolio.exception.ApplicationException;
import com.yass.fin.portfolio.exception.UnAuthorizedException;
import com.yass.fin.portfolio.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	public ResponseDto<Void> handleSQLException(HttpServletRequest request, Exception ex){
		logger.error("SQLException Occurred =========> " +   ex);
		return ResponseUtil.error(ErrorCodes.SRV500, "database_error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDto<Void> handleBadRequestException(MethodArgumentNotValidException ex){
		logger.error("Bad Exception handler executed ===========> " + ex.getMessage());
		
		List<String> errorMsg = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		
		
		return ResponseUtil.error(ErrorCodes.SRV400, String.join(", ", errorMsg), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(ApplicationException.class)
	public Object handleApplicationException(HttpServletRequest request,HttpServletRequest response, ApplicationException ex){
		ex.printStackTrace();
		logger.error("ApplicationException handler executed ========> " + ex.getMessage());
		
		ResponseDto<Void> dto = ResponseUtil.error(ErrorCodes.SRV500, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		if(ex.getErrorCode() == ErrorCodes.SRV403) {
			return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
		} else {
			return dto;
		}
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({AccessDeniedException.class, UnAuthorizedException.class})
	public ResponseDto<Void> handleAccessException(Exception ex){
		ex.printStackTrace();
		logger.error("ApplicationException handler executed ========> " + ex.getMessage());
		return ResponseUtil.error(ErrorCodes.SRV403, ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseDto<Void> handleException(HttpServletRequest request, Exception ex){
		logger.error("Exception handler executed", ex);
		return ResponseUtil.error(ErrorCodes.SRV500, "Technical Error. Please contact admin.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    
//    @ResponseBody
//    @ExceptionHandler
//    public ResponseDto<Void> handleUnknownException(Exception ex) {
//        LOG.info("UNKNOWN EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV500);
//    }
//    @ResponseBody
//    @ExceptionHandler
//    public static ResponseDto<Void> handleRunTimeExceptions(RuntimeException ex) {
//        LOG.info("RUNTIME EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV001);
//    }
//
//
//    @ResponseBody
//    @ExceptionHandler
//    public ResponseDto<Void> handleUnAuthorizedException(UnAuthorizedException ex) {
//        LOG.info("UNAUTHORIZED EXCEPTION: " + ex.getMessage());
//        return ResponseUtil.error(ErrorCodes.SRV403);
//    }
}

package com.cg.dd.app.exception;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	 @ExceptionHandler(value= { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
		 ExceptionResponse response=new ExceptionResponse(LocalDate.now(),ex.getMessage(),request.getDescription(false),400);
			return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object>  handleSQLException(HttpServletRequest request, Exception ex){
		 ExceptionResponse response=new ExceptionResponse(LocalDate.now(),ex.getMessage(),request.getLocalName(),400);
			return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) throws Exception{
		ExceptionResponse response=new ExceptionResponse(LocalDate.now(),ex.getMessage(),request.getDescription(false),400);
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}

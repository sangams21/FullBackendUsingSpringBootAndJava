package com.blogApplication.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogApplication.PayLoads.ApiResponse;

@RestControllerAdvice
public class GlobelExceptionHandler {

	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundExeption ex ){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String ,String>> handelMethodArgsNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String ,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
		String fielName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fielName,message);
			
			
			
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
		
}

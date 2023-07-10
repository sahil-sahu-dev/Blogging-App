package com.sahilsahudev.Blogging.exceptions;

import com.sahilsahudev.Blogging.utils.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        APIResponse response = new APIResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,String>> methodArgsNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> resp = new HashMap<String, String>();
        for(ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError)error).getField();
            resp.put(fieldName, error.getDefaultMessage());
        }

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}

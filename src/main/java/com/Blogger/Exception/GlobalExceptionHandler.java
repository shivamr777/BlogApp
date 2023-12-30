package com.Blogger.Exception;

import com.Blogger.Payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDetail> resourceNotFoundException(
            ResourceNotFoundException exception, WebRequest request
            )
    {

        ErrorDetail Detail=new ErrorDetail();
        Detail.setTimestamp(new Date());
        Detail.setDetails(request.getDescription(false));
        Detail.setMessage(exception.message);
        return new ResponseEntity<>(Detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetail> exceptionHandler(
            Exception exception, WebRequest request
    )
    {

        ErrorDetail Detail=new ErrorDetail();
        Detail.setTimestamp(new Date());
        Detail.setDetails(request.getDescription(false));
        Detail.setMessage(exception.getMessage());
        return new ResponseEntity<>(Detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

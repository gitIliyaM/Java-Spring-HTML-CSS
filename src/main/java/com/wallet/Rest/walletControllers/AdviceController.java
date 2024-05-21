package com.wallet.Rest.walletControllers;

import com.wallet.Rest.exception.RestControllerException;
import com.wallet.Rest.storageExceptions.Storage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    //@ExceptionHandler(RestControllerException.class)
    /*public ResponseEntity<Storage> handleException(RestControllerException e) {
        Storage response = new Storage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    //@Override
    /*protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Storage response = new Storage("Не правильный JSON",ex.getMessage());
        return new ResponseEntity<>(response, status);
        //return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }*/
}

package com.omkar.exception;

import com.omkar.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Handle OrderException
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ApiResponse> handleOrderException(OrderException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ✅ Handle RazorpayException
    @ExceptionHandler(com.razorpay.RazorpayException.class)
    public ResponseEntity<ApiResponse> handleRazorpayException(com.razorpay.RazorpayException ex) {
        ApiResponse response = new ApiResponse("Razorpay error: " + ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ✅ Handle any other Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
        ex.printStackTrace(); // logs full stacktrace
        ApiResponse response = new ApiResponse("Internal Server Error: " + ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

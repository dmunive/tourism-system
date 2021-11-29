package com.tourism.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.INTERNAL_SERVER_ERROR, errorCode = "reservation-service.error")
public class ReservationServiceFailException extends RuntimeException {
}

package com.tourism.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.NOT_FOUND, errorCode = "reservartion.not_found")
public class ReservationNotFoundException extends RuntimeException {}

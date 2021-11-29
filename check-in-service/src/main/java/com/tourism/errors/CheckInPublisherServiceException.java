package com.tourism.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.INTERNAL_SERVER_ERROR, errorCode = "check-in-publisher-service.error")
public class CheckInPublisherServiceException extends RuntimeException {
}

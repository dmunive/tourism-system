package com.tourism.errors;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.INTERNAL_SERVER_ERROR, errorCode = "get-rabbit-mq-message.error")
public class GetRabbitMqMessageFailException extends RuntimeException {
}

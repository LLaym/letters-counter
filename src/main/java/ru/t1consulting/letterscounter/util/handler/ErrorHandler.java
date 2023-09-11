package ru.t1consulting.letterscounter.util.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static ru.t1consulting.letterscounter.util.Constants.FORMATTER;

@Slf4j
@RestControllerAdvice("ru.t1consulting.letterscounter")
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleBadRequestError(final Throwable e) {
        String errorStatus = "BAD_REQUEST";
        String errorReason = "Incorrectly made request.";
        String errorMessage = e.getMessage();
        String errorTimestamp = LocalDateTime.now().format(FORMATTER);

        log.warn("{}. {}", errorReason, errorMessage);
        return new Error(errorStatus, errorReason, errorMessage, errorTimestamp);
    }
}
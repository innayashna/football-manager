package com.example.footballmanager.exeption.handler;

import com.example.footballmanager.exeption.InsufficientFundException;
import com.example.footballmanager.exeption.PlayerNotFoundException;
import com.example.footballmanager.exeption.TeamNotFoundException;
import com.example.footballmanager.exeption.UnsupportedTransferException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseStatusExceptionHandler {

    @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<Object> handleInsufficientFundException(InsufficientFundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({PlayerNotFoundException.class})
    public ResponseEntity<Object> handlePlayerNotFoundException(PlayerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UnsupportedTransferException.class)
    public ResponseEntity<Object> handleUnsupportedTransferException(UnsupportedTransferException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ValidationExceptionDto> collect =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(ValidationExceptionDto::new)
                        .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(collect);
    }
}

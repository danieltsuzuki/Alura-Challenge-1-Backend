package br.com.estudo.alurachallengebackendsemana1.controllers.exception;

import br.com.estudo.alurachallengebackendsemana1.servicies.exception.AtLeastOneFieldNeedToBeFillException;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionTreatment {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError sError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(sError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> badRequest(MethodArgumentNotValidException e,
                                                                               HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Bad request",
                errorMessages.toString(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AtLeastOneFieldNeedToBeFillException.class)
    public ResponseEntity<StandardError> notFound(AtLeastOneFieldNeedToBeFillException e, HttpServletRequest request) {
        StandardError sError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Bad request",
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(sError);
    }
}

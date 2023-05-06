package br.com.estudo.alurachallengebackendsemana1.controllers.exception;

import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> notFound(ResourceNotFound e, HttpServletRequest request) {
        StandardError sError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(sError);

    }
}

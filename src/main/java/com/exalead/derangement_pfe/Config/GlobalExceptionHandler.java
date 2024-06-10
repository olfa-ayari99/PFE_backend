package com.exalead.derangement_pfe.Config;

import com.exalead.derangement_pfe.Exceptions.ExceptionRepresentation;
import com.exalead.derangement_pfe.Validators.ObjectValidationException;
import com.exalead.derangement_pfe.Validators.OperationNonPermittedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.nio.channels.AcceptPendingException;
import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
            return errorDetail;
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
            return errorDetail;
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
            return errorDetail;
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
            return errorDetail;
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }

    @ExceptionHandler({ObjectValidationException.class})
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Object not valid exception has occurred")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("A user already exists with the provided Email")
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionRepresentation> handleDisabledException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("You cannot access your account because it is not yet activated")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Your email and / or password is incorrect")
                .build();
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

}




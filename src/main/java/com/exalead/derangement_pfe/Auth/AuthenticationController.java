package com.exalead.derangement_pfe.Auth;

import com.exalead.derangement_pfe.Entity.ErrorResponse;
import com.exalead.derangement_pfe.Exceptions.ExceptionRepresentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Collections;


@RestController
@CrossOrigin(origins = "http://localhost:64317")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            AuthenticationResponse response = service.register(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationService.UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("validationErrors", e.getValidationErrors()));
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (BadCredentialsException ex) {
            String errorMessage = ex.getMessage();
            ExceptionRepresentation exceptionRepresentation;

            if (errorMessage.contains("Invalid email")) {
                exceptionRepresentation = ExceptionRepresentation.builder()
                        .errorMessage("Invalid email")
                        .errorSource("email")
                        .build();
            } else if (errorMessage.contains("Invalid password")) {
                exceptionRepresentation = ExceptionRepresentation.builder()
                        .errorMessage("Invalid password")
                        .errorSource("password")
                        .build();
            } else {
                exceptionRepresentation = ExceptionRepresentation.builder()
                        .errorMessage("Invalid email or password")
                        .errorSource("general")
                        .build();
            }

            return ResponseEntity.badRequest().body(exceptionRepresentation);
        }
    }




    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}






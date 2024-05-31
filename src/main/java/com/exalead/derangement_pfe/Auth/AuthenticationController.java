package com.exalead.derangement_pfe.Auth;

import com.exalead.derangement_pfe.Entity.ErrorResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@RestController
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
            return ResponseEntity.badRequest().body("User with this email already exists.");
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (BadCredentialsException ex) {
            String errorMessage = ex.getMessage();
            if (errorMessage.contains("Invalid email")) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid email", "Email cannot be empty or has invalid format"));
            } else if (errorMessage.contains("Invalid password")) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid password", "Password must be at least 8 characters long"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Unauthorized", "Invalid email or password"));
            }
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






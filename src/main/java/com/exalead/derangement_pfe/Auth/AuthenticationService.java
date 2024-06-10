package com.exalead.derangement_pfe.Auth;

import com.exalead.derangement_pfe.Config.JwtService;
import com.exalead.derangement_pfe.Entity.Role;
import com.exalead.derangement_pfe.Entity.Token;
import com.exalead.derangement_pfe.Entity.TokenType;
import com.exalead.derangement_pfe.Repository.TokenRepository;
import com.exalead.derangement_pfe.Repository.UserRepository;
import com.exalead.derangement_pfe.Validators.ObjectsValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.exalead.derangement_pfe.Entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectsValidator<RegisterRequest> validator;


    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistsException {
    validator.validate(request);

        if (repository.existsByEmail(request.getEmail())) {
            // If user with the provided email already exists, throw an exception
            throw new UserAlreadyExistsException("User with this email already exists.");
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .tokenType("BEARER")
               // .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("Invalid email")) {
                throw new BadCredentialsException("Invalid email");
            } else if (ex.getMessage() != null && ex.getMessage().contains("Invalid password")) {
                throw new BadCredentialsException("Invalid password");
            } else {
                throw ex;
            }
        }

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        // Ensure the user has a role
        if (user.getRole() == null) {
            throw new IllegalStateException("User role is not assigned");
        }


        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }




    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getIdUser()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)

                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    class UserAlreadyExistsException extends RuntimeException {
        private final List<String> validationErrors;
        public UserAlreadyExistsException(String message) {
            super(message);
            this.validationErrors = Collections.singletonList(message);
        }
        public List<String> getValidationErrors() {
            return validationErrors;
        }
    }


}

package com.exalead.derangement_pfe;

import com.exalead.derangement_pfe.Auth.AuthenticationService;
import com.exalead.derangement_pfe.Auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import static com.exalead.derangement_pfe.Entity.Role.ADMIN;

@SpringBootApplication
@EnableJpaAuditing
public class DerangementPfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DerangementPfeApplication.class, args);
    }

  /*  @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }*/

}


package com.exalead.derangement_pfe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.exalead.derangement_pfe.Entity.Role.ADMIN;

@SpringBootApplication

@EnableJpaAuditing


public class DerangementPfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DerangementPfeApplication.class, args);

        //searchCustomer();
    }



   /* private static final Logger logger = LoggerFactory.getLogger(ClientImpacteService.class);
    public static void searchCustomer() {
        try {
            // Initialize the search API client
            SearchAPIClient client = SearchAPIClientFactory.build("http://149.202.64.60:16010/");
            SearchClient searchClient = client.searchClient("search-api");


            //logger.debug("Notification client: Joyeux Anniversaire  " + query);
            SearchQuery searchQuery = new SearchQuery("class:person");
            QueryUtils.addPerformanceOptions(searchQuery);
            QueryUtils.disableSynthesis(searchQuery);
            searchQuery.addParameter(SearchParameter.NRESULTS, "-1");


            SearchAnswer searchAnswer = searchClient.getResults(searchQuery);


            List<Hit> hits = searchAnswer.getHits();
            if (!hits.isEmpty()){
                for (Hit hit : hits){

                    String idClient = hit.getMeta("customerid").getStringValue();
                    String fullName = hit.getMeta("email").getStringValue();
                    System.out.println("affiche"+ idClient);

                }
            }


        } catch (Exception e) {
            logger.error("Error during search", e);
        }
    }*/

   /*@Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@admin.com")
                    .password("admin")

                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }*/

}



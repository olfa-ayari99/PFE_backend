package com.exalead.derangement_pfe.Service.ClientImpacteService;

import com.exalead.derangement_pfe.Entity.ClientImpacte;
import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Repository.ClientImpacteRepository;
import com.exalead.derangement_pfe.Repository.DerangementRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
public class ClientImpacteService implements IClientImpacteService {

    @Autowired
    ClientImpacteRepository clientImpacteRepository;
    @Autowired
    DerangementRepository derangementRepository;

    /*SearchAPIClient client = SearchAPIClientFactory.build("http://149.202.64.60:16010/search-api/search");
    SearchClient searchClient = client.searchClient("search-api");
    SearchQuery sq = new SearchQuery("class:person");
    SearchAnswer sa = searchClient.getResults(sq);

    SearchClient searchClient = null;
		try {
        searchClient = SearchClientFactory.createSearchClient(sapi, SearchAPIVersion.V6R2018X);
        String query = "class:(individual) AND customer_guid:\"" + customerId + "\"";
        logger.debug("Notification client: Joyeux Anniversaire  " + query);
        SearchQuery searchQuery = new SearchQuery(query);
        QueryUtils.addPerformanceOptions(searchQuery);
        QueryUtils.disableSynthesis(searchQuery);
        searchQuery.addParameter(SearchParameter.NRESULTS, "-1");
        SearchAnswer searchAnswer = searchClient.getResults(searchQuery);
        List<Hit> hits = searchAnswer.getHits();
        if (!hits.isEmpty()) {
            for (Hit hit : hits) {
                if (hit.getMeta("birthdate") != null && !hit.getMeta("birthdate").getStringValue().isEmpty()) {
                    String birthDate = hit.getMeta("birthdate").getStringValue();
                    String fullName = hit.getMeta("full_name").getStringValue();
                    String civility = "";
                    if (hit.getMeta("civility") != null && !hit.getMeta("civility").getStringValue().isEmpty())
                        civility = hit.getMeta("civility").getStringValue();
                    if (customerBirthDate(birthDate))
                        individualJson.put(Utils.notificationToJson("Generale", "#000000",
                                "Joyeux Anniversaire à " + civility + " " + fullName + " ! ", "9"));
                }
            }
        }
    }*/










   /* @Override
    public ClientImpacte addClient(ClientImpacte client) {
        ClientImpacte c = clientImpacteRepository.save(client);
        return c;
    }


    @Override
    public void deleteClient(Long idClient) {
        clientImpacteRepository.deleteById(idClient);
    }

    @Override
    public ClientImpacte updateClient(ClientImpacte c) {
        return clientImpacteRepository.save(c);
    }

    @Override
    public List<ClientImpacte> getAllClients() {
        List<ClientImpacte> clients = clientImpacteRepository.findAll();
        return clients;
    }

    @Override
    public ClientImpacte getClient(Long idClient) {
        ClientImpacte c = clientImpacteRepository.findById(idClient).orElse(null);
        return c;
    }*/

   // @Autowired
    //private final RestTemplate restTemplate;

   /* @Override
    public List<ClientImpacte> fetchClientImpactesFromApi() throws JAXBException {
        String url = "http://149.202.64.60:16010/search-api/search?q=class:person";
        String xmlResponse = restTemplate.getForObject(url, String.class);

        JAXBContext jaxbContext = JAXBContext.newInstance(ClientImpacteResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ClientImpacteResponse response = (ClientImpacteResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));

        List<ClientImpacte> clientImpactes = new ArrayList<>();
        for (ClientImpacteResponse.Hit hit : response.getHits()) {
            ClientImpacte clientImpacte = new ClientImpacte();
            for (ClientImpacteResponse.Meta meta : hit.getMetas()) {
                switch (meta.getName()) {
                    case "firstname":
                        clientImpacte.setFirstname(meta.getMetaString().getValue());
                        break;
                    case "lastname":
                        clientImpacte.setLastname(meta.getMetaString().getValue());
                        break;

                }
            }
            clientImpactes.add(clientImpacte);
        }

        return clientImpactes;
    }
*/


    public void affectClientsToDerangement(Long idDerangement, List<Long> clientIds) {
        Derangement d = derangementRepository.findById(idDerangement).orElseThrow(() -> new RuntimeException("Dérangement non trouvé"));

        Set<ClientImpacte> clientImpactes = new HashSet<>();
        for (Long idClient : clientIds) {
            ClientImpacte c = clientImpacteRepository.findById(idClient).orElseThrow(() -> new RuntimeException("Client non trouvé"));
            clientImpactes.add(c);
        }

        d.getClientImpactes().addAll(clientImpactes);

        derangementRepository.save(d);
    }





}

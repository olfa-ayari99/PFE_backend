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

    @Override
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
    }

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

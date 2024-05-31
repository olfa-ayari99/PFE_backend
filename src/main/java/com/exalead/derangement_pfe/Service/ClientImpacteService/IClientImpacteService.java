package com.exalead.derangement_pfe.Service.ClientImpacteService;

import com.exalead.derangement_pfe.Entity.ClientImpacte;

import java.util.List;

public interface IClientImpacteService {

    public ClientImpacte addClient(ClientImpacte client);
    public void deleteClient(Long idClient);
    public ClientImpacte updateClient(ClientImpacte c);
    public List<ClientImpacte> getAllClients();
    public ClientImpacte getClient(Long idClient);
    public void affectClientsToDerangement(Long idDerangement, List<Long> clientIds);


}

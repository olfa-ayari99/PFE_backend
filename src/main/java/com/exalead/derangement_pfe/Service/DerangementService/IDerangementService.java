package com.exalead.derangement_pfe.Service.DerangementService;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Statut;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDerangementService {

    public Derangement addDerangement(Derangement derangement, Long userId, Long idEquip);
    public void deleteDerangement(Long idDerangement);
    public Derangement updateDerangement(Long idDerangement, Derangement d);
    public List<Derangement> getAllDerangements();
    public Derangement getDerangement(Long idDerangement);

    public List<Derangement> findDerangementByStatus(Statut statut);
    public List<Derangement> findDerangementByClient (Long idClient);
    public List<Derangement> searchDerangements(Map<String, Object> criteria);

    public  List<Derangement> getDerangementByUser (Long idUser);

    public Page<Derangement> getDerangements(int page, int size);


}

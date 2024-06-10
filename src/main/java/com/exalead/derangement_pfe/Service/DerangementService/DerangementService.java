package com.exalead.derangement_pfe.Service.DerangementService;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Equipement;
import com.exalead.derangement_pfe.Entity.Statut;
import com.exalead.derangement_pfe.Entity.User;
import com.exalead.derangement_pfe.Repository.DerangementRepository;
import com.exalead.derangement_pfe.Repository.LocalisationRepository;
import com.exalead.derangement_pfe.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class DerangementService implements IDerangementService {


    @Autowired
    DerangementRepository derangementRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocalisationRepository localisationRepository;

    public Derangement addDerangement(Derangement derangement, Long userId, Long idEquip) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Equipement equipement = localisationRepository.findById(idEquip).orElseThrow(() -> new RuntimeException("Équipement non trouvé"));

        derangement.setUser(user);
        derangement.setEquipement(equipement);

        return derangementRepository.save(derangement);
    }


    @Override
    public void deleteDerangement(Long idDerangement){
        derangementRepository.deleteById(idDerangement);
    }

    @Override
    public Derangement updateDerangement(Derangement d){
        return derangementRepository.save(d);
    }

    @Override
    public List<Derangement> getAllDerangements(){
        List<Derangement> derangements= derangementRepository.findAll();
        return derangements;
    }

    @Override
    public Derangement getDerangement(Long idDerangement){
        Derangement d = derangementRepository.findById(idDerangement).orElse(null);
        return d;
    }

    @Override
    public List<Derangement> getDerangementByUser (Long idUser){
        return derangementRepository.findByUser_IdUser(idUser);

    }
    @Override
    public List<Derangement> findDerangementByStatus(Statut statut){
        return derangementRepository.findByStatut(statut);
    }


    @Override
    public List<Derangement> findDerangementByClient (Long idClient){
        return derangementRepository.findByClientImpactes_IdClient(idClient);
    }
    public List<Derangement> searchDerangements(Map<String, Object> criteria) {
        if (criteria.containsKey("statut") && criteria.containsKey("idClient")) {
            Statut statut = Statut.valueOf((String) criteria.get("statut"));
            Long idClient = Long.valueOf(criteria.get("idClient").toString());
            return derangementRepository.findByStatutAndClientImpactes_IdClient(statut, idClient);
        } else if (criteria.containsKey("statut")) {
            Statut statut = Statut.valueOf((String) criteria.get("statut"));
            return derangementRepository.findByStatut(statut);
        } else if (criteria.containsKey("idClient")) {
            Long idClient = Long.valueOf(criteria.get("idClient").toString());
            return derangementRepository.findByClientImpactes_IdClient(idClient);
        } else {
            return derangementRepository.findAll();
        }
    }







}

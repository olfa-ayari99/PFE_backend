package com.exalead.derangement_pfe.Service.LocalisationService;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Equipement;
import com.exalead.derangement_pfe.Entity.Offre;
import com.exalead.derangement_pfe.Entity.User;
import com.exalead.derangement_pfe.Repository.DerangementRepository;
import com.exalead.derangement_pfe.Repository.LocalisationRepository;
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
public class LocalisationService implements ILocalisationService {

    @Autowired
    LocalisationRepository localisationRepository;

    @Autowired
    DerangementRepository derangementRepository;

    @Override
    public Equipement addLocation (Equipement location){
        Equipement l =localisationRepository.save(location);
        return l;
    }


    @Override
    public void deleteLocation(Long idEquip){
        localisationRepository.deleteById(idEquip);
    }

    @Override
    public Equipement updateLocation(Equipement l){
        return localisationRepository.save(l);
    }

    @Override
    public List<Equipement> getAllLocations(){
        List<Equipement> locations= localisationRepository.findAll();
        return locations;
    }

    @Override
    public Equipement getLocation(Long idEquip){
        Equipement l = localisationRepository.findById(idEquip).orElse(null);
        return l;
    }






}

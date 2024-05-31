package com.exalead.derangement_pfe.Service.OffreService;

import com.exalead.derangement_pfe.Entity.Equipement;
import com.exalead.derangement_pfe.Entity.Offre;
import com.exalead.derangement_pfe.Repository.OffreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class OffreService implements IOffreService {

    @Autowired
    OffreRepository offreRepository;

    @Override
    public Offre addOffre (Offre offre){
        Offre o = offreRepository.save(offre);
        return o;
    }

    @Override
    public void deleteOffre(Long idOffre){
        offreRepository.deleteById(idOffre);
    }

    @Override
    public Offre updateOffre(Offre o){
        return offreRepository.save(o);
    }

    @Override
    public List<Offre> getOffres(){
        List<Offre> offres= offreRepository.findAll();
        return offres;
    }

    @Override
    public Offre getOffre(Long idOffre){
        Offre o = offreRepository.findById(idOffre).orElse(null);
        return o;
    }
}

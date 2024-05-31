package com.exalead.derangement_pfe.Service.OffreService;

import com.exalead.derangement_pfe.Entity.Offre;

import java.util.List;

public interface IOffreService {

    public Offre addOffre (Offre offre);
    public void deleteOffre(Long idOffre);
    public Offre updateOffre(Offre o);
    public List<Offre> getOffres();
    public Offre getOffre(Long idOffre);

}

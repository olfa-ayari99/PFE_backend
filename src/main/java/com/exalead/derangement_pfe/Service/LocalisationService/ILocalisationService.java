package com.exalead.derangement_pfe.Service.LocalisationService;

import com.exalead.derangement_pfe.Entity.Equipement;

import java.util.List;

public interface ILocalisationService {

    public Equipement addLocation (Equipement location);
    public void deleteLocation(Long idEquip);
    public Equipement updateLocation(Equipement l);
    public List<Equipement> getAllLocations();
    public Equipement getLocation(Long idEquip);

}

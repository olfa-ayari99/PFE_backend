package com.exalead.derangement_pfe.Repository;

import com.exalead.derangement_pfe.Entity.Derangement;
import com.exalead.derangement_pfe.Entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DerangementRepository extends JpaRepository<Derangement,Long> {
    List<Derangement> findByStatut(Statut statut);
    List<Derangement> findByClientImpactes_IdClient(Long idClient);
    List<Derangement> findByStatutAndClientImpactes_IdClient(Statut statut, Long idClient);

    List<Derangement> findByUser_IdUser(Long idUser);




}

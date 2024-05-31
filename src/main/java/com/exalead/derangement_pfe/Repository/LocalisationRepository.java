package com.exalead.derangement_pfe.Repository;

import com.exalead.derangement_pfe.Entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends JpaRepository<Equipement,Long> {
}

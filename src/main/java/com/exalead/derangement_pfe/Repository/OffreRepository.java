package com.exalead.derangement_pfe.Repository;

import com.exalead.derangement_pfe.Entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
}

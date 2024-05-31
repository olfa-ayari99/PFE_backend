package com.exalead.derangement_pfe.Repository;

import com.exalead.derangement_pfe.Entity.ClientImpacte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientImpacteRepository extends JpaRepository<ClientImpacte,Long> {
}

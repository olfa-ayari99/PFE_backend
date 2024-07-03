package com.exalead.derangement_pfe.Repository;

import com.exalead.derangement_pfe.Entity.Offre;
import com.exalead.derangement_pfe.Entity.Role;
import com.exalead.derangement_pfe.Entity.User;
import com.google.api.gax.paging.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u  WHERE u.firstname LIKE %:firstname%")
    List<User> FindByfirstname(@Param("firstname") String firstname);


    //Page<User> findAll(Pageable pageable);


}

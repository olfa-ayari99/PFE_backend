package com.exalead.derangement_pfe.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Table(name ="Offre")
public class Offre  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;
    private String nomOffre;

    @OneToMany(mappedBy = "offre", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    Set<User> users;

}

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
@Table(name = "Equipement")
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idEquip;
    private  String nomEquipement;
    private String addresse;
    private Double longitude;
    private Double latitude;
    private String ville;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipement")
    private Set<Derangement> derangements;

    public void setIdLocal(Long idLocal) {
        this.idEquip = idEquip;
    }

    public Long getIdLocal() {
        return idEquip;
    }
}

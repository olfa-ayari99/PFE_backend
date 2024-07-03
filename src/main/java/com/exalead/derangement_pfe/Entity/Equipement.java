package com.exalead.derangement_pfe.Entity;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Table(name = "Equipement")
@EntityListeners(AuditingEntityListener.class)
public class Equipement  implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idEquip;
    private  String nomEquipement;
    private String addresse;
    private Double longitude;
    private Double latitude;
    private String ville;


    @CreatedDate
    @Column(
            name = "createdDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private LocalDateTime lastModifiedDate;
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "equipement")
    private Set<Derangement> derangements;



    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getIdEquip() {
        return idEquip;
    }
}

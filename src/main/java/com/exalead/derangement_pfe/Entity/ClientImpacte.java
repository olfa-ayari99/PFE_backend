package com.exalead.derangement_pfe.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Table(name = "ClientImpacte")
public class ClientImpacte  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String login;
    private String ip;

    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;

    private String contact1;
    private String conatact2;
    private String libelleOffre;
    private String numeroLigne;


    @ManyToMany(mappedBy = "clientImpactes", cascade = CascadeType.ALL)
    @JsonIgnore
     private Set<Derangement> derangements;


    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdClient() {
        return idClient;
    }
}

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
    @Table(name ="Derangement")
    public class Derangement implements Serializable {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idDerangement;
        private String nomDerangement;

        @Temporal(TemporalType.DATE)
        private Date  dateCreationDer;

        private String reseau;
        private String serviceImpacte;
        private String cause;
        @Temporal (TemporalType.DATE)
        private Date dateDebutDerangement;
        @Temporal (TemporalType.DATE)
        private Date dateConstat;

        private String  delaiPrevisionnel;

        @Temporal (TemporalType.DATE)
        private Date  dateResolutionPrevu;

        @Temporal (TemporalType.DATE)
        private Date  dateResolutionReel;

        private String porteur;



        @Enumerated(EnumType.STRING)
        private Statut statut;

        private String profilePlateauConcerne;
        private String longitude;
        private String latidude;

        private String script;


        @ManyToOne
        private Equipement equipement;

        @ManyToMany(cascade = CascadeType.ALL)
        @JsonIgnore
        @JoinTable(
                name = "client_derangement",
                joinColumns = @JoinColumn(name = "derangement_id"),
                inverseJoinColumns = @JoinColumn(name = "client_id")
        )
        private Set<ClientImpacte> clientImpactes;

        @ManyToOne
        private User user;


        public void setIdDerangement(Long idDerangement) {
            this.idDerangement = idDerangement;
        }

        public Long getIdDerangement() {
            return idDerangement;
        }
    }
package com.exalead.derangement_pfe.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.time.LocalDateTime;
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
    @EntityListeners(AuditingEntityListener.class)
    public class Derangement  implements Serializable {


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
        private Double longitude;
        private Double latidude;

        private String script;




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


        @ManyToOne
        private Equipement equipement;

        @ManyToMany
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

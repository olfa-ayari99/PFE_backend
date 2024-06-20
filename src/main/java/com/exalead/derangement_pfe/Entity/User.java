package com.exalead.derangement_pfe.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Data
@Table(name = "User")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotNull(message = "Le prenom ne doit pas etre vide")
    @NotEmpty(message = "Le prenom ne doit pas etre vide")
    @NotBlank(message = "Le prenom ne doit pas etre vide")
    private String firstname;
    @NotNull(message = "Le nom ne doit pas etre vide")
    @NotEmpty(message = "Le nom ne doit pas etre vide")
    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String lastname;
    private String username;
    @NotNull(message = "L'email ne doit pas etre vide")
    @NotEmpty(message = "L'email ne doit pas etre vide")
    @NotBlank(message = "L'email ne doit pas etre vide")
    @Email(message = "L'email n'est conforme")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
    @NotBlank(message = "Le mot de passe ne doit pas etre vide")
    private String password;
    private String address;
    private String telNum;



    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Derangement> derangementSet;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;

    @ManyToOne
    private Offre offre;

    public Role getRole() {
        if (this.role == null) {
            this.role = Role.ADMIN; // Assign a default role
        }
        return this.role;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

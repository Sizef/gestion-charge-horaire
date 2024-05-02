package com.project.gestion_charge_horaire.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enseignant {

    @Id
    private String email;
    private String nom;
    private String prenom;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.MERGE)
    public List<Intervention> interventions;

    @JsonManagedReference
    @OneToMany(mappedBy = "enseignant" , cascade = CascadeType.MERGE)
    private List<Role> roles;
}

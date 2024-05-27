package com.project.gestion_charge_horaire.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

package com.project.gestion_charge_horaire.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomFiliere;

    @JsonIgnore
    @OneToMany(mappedBy = "filiere" , cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Module> modules;
}

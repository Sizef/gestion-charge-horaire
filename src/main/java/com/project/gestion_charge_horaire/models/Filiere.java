package com.project.gestion_charge_horaire.models;

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
public class Filiere {

    @Id
    private String nomFiliere;

    @OneToMany(mappedBy = "filiere")
    public List<Module> modules;
}

package com.project.gestion_charge_horaire.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Module {

    @Id
    private String intitule;
    private int vh_cours;
    private int vh_td;
    private int vh_tp;
    private int evaluation;

    @OneToMany(mappedBy = "module")
    public List<Intervention> interventions;

    @ManyToOne
    public Filiere filiere;
}

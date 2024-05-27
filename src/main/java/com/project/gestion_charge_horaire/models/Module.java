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
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    private int vh_cours;
    private int vh_td;
    private int vh_tp;
    private int evaluation;

    @JsonManagedReference
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Intervention> interventions;

    @ManyToOne
    public Filiere filiere;
}

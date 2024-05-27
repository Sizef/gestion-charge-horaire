package com.project.gestion_charge_horaire.models;

import lombok.Data;

@Data
public class FiliereInfos {

    private Long id;
    private String nom;
    private int nbreModules;
    private int nbreEnseignants;
}

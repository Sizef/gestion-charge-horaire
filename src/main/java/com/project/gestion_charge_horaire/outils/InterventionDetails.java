package com.project.gestion_charge_horaire.outils;

import lombok.Data;

@Data
public class InterventionDetails {

    private String nompComplet;
    private String intitule;
    private int evaluation;
    private int cours;
    private int td;
    private int tp;
}

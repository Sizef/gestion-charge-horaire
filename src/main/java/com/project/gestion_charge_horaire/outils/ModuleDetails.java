package com.project.gestion_charge_horaire.outils;

import lombok.Data;

@Data
public class ModuleDetails {

    private int id;
    private String intitule;
    private int vh_cours;
    private int vh_td;
    private int vh_tp;
    private int evaluation;
    private String nomFiliere;
}

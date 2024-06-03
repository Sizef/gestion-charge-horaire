package com.project.gestion_charge_horaire.models;

import lombok.Data;

@Data
public class Module_interventions {

    private int moduleId;
    private int enseignantId;
    private int vh_cours_intervention;
    private int vh_td_inetervention;
    private int vh_tp_inetervention;
    private int evaluation_inetervention;
}

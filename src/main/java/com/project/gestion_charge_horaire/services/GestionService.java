package com.project.gestion_charge_horaire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionService {

    @Autowired
    FiliereService filiereService;

    @Autowired
    ModuleService moduleService;

    @Autowired
    EnseignantService enseignantService;

    @Autowired
    InterventionService interventionService;

}

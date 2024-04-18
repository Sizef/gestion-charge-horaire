package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;
}

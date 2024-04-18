package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiliereController {

    @Autowired
    private FiliereService filiereService;
}

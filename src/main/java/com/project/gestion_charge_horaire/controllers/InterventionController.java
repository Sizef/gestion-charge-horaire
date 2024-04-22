package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InterventionController {

    @Autowired
    private InterventionService interventionService;
    @Autowired
    private InterventionRepository interventionRepository;

    @PostMapping("/inter/{intitule}")
    public List<Enseignant> getAllInterventions(@PathVariable String intitule) {
        List<Intervention> interventions = interventionRepository.findInterventionByModuleIntitule(intitule);
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants;
    }


}

package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.outils.InterventionDetails;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InterventionController {

    @Autowired
    private InterventionService interventionService;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private EnseignantService enseignantService;

    @PostMapping("/inter/{intitule}")
    public List<Enseignant> getAllInterventions(@PathVariable String intitule) {
        List<Intervention> interventions = interventionRepository.findInterventionByModuleIntitule(intitule);
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants;
    }

    @GetMapping("/interventions")
    public List<InterventionDetails> getAllInterventions() {
         List<Intervention> interventions = interventionRepository.findAll();
         List<InterventionDetails> interventionDetails = new ArrayList<>();
         for (Intervention intervention : interventions) {
             InterventionDetails interventionDetail = new InterventionDetails();
             Enseignant enseignant = enseignantService.findByEmail(intervention.getEnseignant().getEmail());
             interventionDetail.setNompComplet(enseignant.getPrenom() + " " + enseignant.getNom());
             interventionDetail.setIntitule(intervention.getModule().getIntitule());
             interventionDetail.setEvaluation(intervention.getEvaluation_inetervention());
             interventionDetail.setCours(intervention.getVh_cours_intervention());
             interventionDetail.setTd(intervention.getVh_td_inetervention());
             interventionDetail.setTp(intervention.getVh_tp_inetervention());
             interventionDetails.add(interventionDetail);
         }
        return interventionDetails;
    }


}

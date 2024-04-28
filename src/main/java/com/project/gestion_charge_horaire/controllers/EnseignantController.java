package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonMixinModuleEntries;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;
    private EnseignantRepository enseignantRepository;


    @PostMapping("/login")
    public Enseignant login(@RequestBody String email) {
        Enseignant enseignant = new Enseignant();
        for (Enseignant e : enseignantService.findAll()) {
            String email2 = e.getEmail();
            boolean AreEqual = (email2.equals(email));
            if (AreEqual) {
                enseignant.setEmail(e.getEmail());
                enseignant.setNom(e.getNom());
                enseignant.setPrenom(e.getPrenom());
            }
        }

        return enseignant;

    }


    // get all enseignants
    @GetMapping("/enseigants")
    public String getAllEnseignants(){
        return enseignantService.getEnseignants();
    }


    // get enseignants by filiere
    @PostMapping("/filiere/enseignants")
    public List<Enseignant> findEnseignantsByFiliere(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        List<Intervention> interventions = new ArrayList<>();
        for (Module module : modules) {
            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
        }
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants;
    }

    // get nbre enseignants by filiere
    @PostMapping("/filiere/enseignants/nbre")
    public int findNbreEnseignantsByFiliere(@RequestBody Filiere filiere) {
        return enseignantService.nombreEnseignantsByFiliere(filiere);
    }

}

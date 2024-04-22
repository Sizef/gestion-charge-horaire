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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private EnseignantRepository enseignantRepository;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private JsonMixinModuleEntries jsonMixinModuleEntries;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;


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
    public String findNbreEnseignantsByFiliere(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        List<Intervention> interventions = new ArrayList<>();
        for (Module module : modules) {
            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
        }
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return "Le nombre des enseignants dans cette filière est : "  + enseignants.size();
    }

}

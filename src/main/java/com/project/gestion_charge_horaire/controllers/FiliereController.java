package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.services.FiliereService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import org.hibernate.validator.constraintvalidators.RegexpURLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FiliereController {

    @Autowired
    private FiliereService filiereService;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;


    @GetMapping("/filieres")
    public List<Filiere> getAllFilieres() {
       if (filiereService.findAllFilieres().isEmpty())
           return null;
       else
           return filiereService.findAllFilieres();

    }

    // get enseignants for module
    @PostMapping("/filiere/enseignants_pour_module")
    public Map<String , Enseignant> getEnseignantsPourModule(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        List<Enseignant> enseignants = new ArrayList<>();
        Map<String , Enseignant> enseignantsPourModule = new HashMap<>();
        for (Module module : modules) {
            for (Intervention intervention : module.getInterventions()) {
                enseignantsPourModule.put(module.getIntitule(),intervention.getEnseignant());
            }
        }
        return enseignantsPourModule;
    }
}

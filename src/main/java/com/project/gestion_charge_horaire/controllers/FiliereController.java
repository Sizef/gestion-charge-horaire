package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.FiliereService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    @Autowired
    private EnseignantService enseignantService;


    @GetMapping("/filiere")
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




    @GetMapping("/filieres/infos")
    public List<FiliereInfos> getFilieres() {
        // tous les filières
        List<Filiere> filieres = filiereService.findAllFilieres();
        System.out.println(filieres.size());
        //filière infos

        //list d'infos
        List<FiliereInfos> filiereInfosList = new ArrayList<>();

        for(Filiere filiere : filieres) {
            FiliereInfos filiereInfos = new FiliereInfos();
            filiereInfos.setNom(filiere.getNomFiliere());
            filiereInfos.setNbreModules(moduleService.nombreModulesByFiliere(filiere));
            filiereInfos.setNbreEnseignants(enseignantService.nombreEnseignantsByFiliere(filiere));
            filiereInfosList.add(filiereInfos);
        }

        return filiereInfosList;

    }
}

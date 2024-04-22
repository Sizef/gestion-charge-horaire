package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    // get all enseignants
    @GetMapping("/modules")
    public String getAllModules(){
        return moduleService.getModules();
    }


    // get modues by filiere
    @PostMapping("/filiere/modules")
    public List<Module> findModulesByFiliere(@RequestBody Filiere filiere) {
        return moduleService.findModulesByFiliere(filiere);
    }

    // get nbre modues by filiere
    @PostMapping("/filiere/modules/nbre")
    public String findNbreModulesByFiliere(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        return "Le nombre des modules dans cette filière est : " + modules.size();
    }
}

package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public List<Module> findModulesByFiliere(Filiere filiere) {
        return moduleRepository.findModulesByFiliere(filiere);
    }

    public String getModules() {
        if(moduleRepository.findAll().toArray().length == 0){
            return "There is no Modules in the database";
        }else {
            return "There are " + moduleRepository.findAll().toArray().length + " Modules";
        }
    }
}

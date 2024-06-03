package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public List<Module> getModulesByFiliere(Filiere filiere) {
        return moduleRepository.getModulesByFiliere(filiere);
    }

    public Module getModuleById(Long id) {
        Optional<Module> Opt = moduleRepository.findById(id);
        return Opt.orElse(null);
    }

    public int nombreModulesByFiliere(Filiere filiere) {
        return moduleRepository.countModulesByFiliere(filiere);
    }
}

package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    @Autowired
    InterventionRepository interventionRepository;

    public List<Intervention> findInterventionByModuleIntitule(String intitule) {
        return interventionRepository.findInterventionByModuleIntitule(intitule);
    }

    public List<Intervention> findInterventionsByModule(Module module) {
        return interventionRepository.findInterventionsByModule(module);
    }
}

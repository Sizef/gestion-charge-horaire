package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;

    public Enseignant findByEmail(String email) {
        return enseignantRepository.findByEmail(email);
    }

    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }

    public int nombreEnseignantsByFiliere(Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        List<Intervention> interventions = new ArrayList<>();
        for (Module module : modules) {
            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
        }
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants.size();    }



    public String getEnseignants() {
        if(enseignantRepository.findAll().toArray().length == 0){
            return "There is no Enseignants in the database";
        }else {
            return "There are " + enseignantRepository.findAll().toArray().length + " Enseignants";
        }
    }
}

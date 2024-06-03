package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Enseignant findByEmailAndPassword(String email , String password) {
        return enseignantRepository.findByEmailAndPassword(email , password);
    }

    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }

    public int nombreEnseignantsByFiliere(Filiere filiere) {
        List<Module> modules = moduleService.getModulesByFiliere(filiere);
        Set<Enseignant> enseignants = new HashSet<>();
        List<Intervention> interventions = new ArrayList<>();
        for (Module module : modules) {
            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
        }
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants.size();
    }


    public Enseignant enseignantInfos(String email) {
        return enseignantRepository.findByEmail(email);
    }

//    public List<Enseignant> findAllEnseignantsWithRoles() {
//        return enseignantRepository.findAllEnseignantsWithRoles();
//    }

    public String getEnseignants() {
        if(enseignantRepository.findAll().toArray().length == 0){
            return "There is no Enseignants in the database";
        }else {
            return "There are " + enseignantRepository.findAll().toArray().length + " Enseignants";
        }
    }

    public Enseignant getEnseignantById(Long id) {
        Optional<Enseignant> Opt = enseignantRepository.findById(id);
        return Opt.orElse(null);
    }
}

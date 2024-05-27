package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FiliereService {

    @Autowired
    FiliereRepository filiereRepository;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private InterventionService interventionService;
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Filiere> findAllFilieres() {
        return filiereRepository.findAll();
    }

    @Transactional
    public boolean deleteFiliere(Long id) {
        Optional<Filiere> Optfiliere = filiereRepository.findById(id);
        if (Optfiliere.isPresent()) {
            Filiere filiere = Optfiliere.get();
            System.err.println("le filiere est : " + filiere.getNomFiliere());
            List<Module> modules =  moduleService.getModulesByFiliere(filiere);
            System.err.println("test------- " + modules.size());
            for (Module module :modules) {
                System.err.println("loop start");
                List<Intervention> interventions = interventionRepository.findInterventionsByModule(module);
                System.err.println("size : " + interventions.size());
                interventionService.deleteInterventionByModule(module);
                //interventionRepository.deleteAll(interventions);
                moduleRepository.delete(module);
                System.err.println("***********************");
            }
            System.err.println("@@@@@@@@@@@@@@@");
            filiereRepository.delete(filiere);

            return true;
        } else {

            return false;
        }


    }




}

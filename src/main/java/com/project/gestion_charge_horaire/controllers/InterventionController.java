package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.InterventionId;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.models.Module_interventions;
import com.project.gestion_charge_horaire.outils.InterventionDetails;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class InterventionController {

    @Autowired
    private InterventionService interventionService;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @PostMapping("/inter/{intitule}")
    public List<Enseignant> getAllInterventions(@PathVariable String intitule) {
        List<Intervention> interventions = interventionRepository.findInterventionByModuleIntitule(intitule);
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants;
    }

    @GetMapping("/interventions")
    public List<InterventionDetails> getAllInterventions() {
         List<Intervention> interventions = interventionRepository.findAll();
         List<InterventionDetails> interventionDetails = new ArrayList<>();
         for (Intervention intervention : interventions) {
             InterventionDetails interventionDetail = new InterventionDetails();
             Enseignant enseignant = enseignantService.findByEmail(intervention.getEnseignant().getEmail());
             interventionDetail.setNompComplet(enseignant.getPrenom() + " " + enseignant.getNom());
             interventionDetail.setIntitule(intervention.getModule().getIntitule());
             interventionDetail.setEvaluation(intervention.getEvaluation_inetervention());
             interventionDetail.setCours(intervention.getVh_cours_intervention());
             interventionDetail.setTd(intervention.getVh_td_inetervention());
             interventionDetail.setTp(intervention.getVh_tp_inetervention());
             interventionDetails.add(interventionDetail);
         }
        return interventionDetails;
    }

    @PostMapping("/interventions/create")
    public Map<String, String> createIntervention(@RequestBody Module_interventions intervention) {
        Map<String, String> response = new HashMap<>();
        if(interventionRepository.existsByEnseignant_IdAndModule_Id((long) intervention.getEnseignantId(), (long) intervention.getModuleId())) {
            response.put("message", "intervention exist");
        } else {
            Module module = moduleService.getModuleById((long) intervention.getModuleId());
            Enseignant enseignant = enseignantService.getEnseignantById((long) intervention.getEnseignantId());

            InterventionId interventionId = new InterventionId();
            interventionId.setId_module((long) intervention.getModuleId());
            interventionId.setId_enseignant((long) intervention.getEnseignantId());

            Intervention intervention1 = new Intervention();
            intervention1.setInterventionId(interventionId);
            intervention1.setVh_cours_intervention(intervention.getVh_cours_intervention());
            intervention1.setVh_td_inetervention(intervention.getVh_td_inetervention());
            intervention1.setVh_tp_inetervention(intervention.getVh_tp_inetervention());
            intervention1.setEvaluation_inetervention(intervention.getEvaluation_inetervention());
            intervention1.setEnseignant(enseignant);
            intervention1.setModule(module);

            interventionRepository.save(intervention1);
        }
        response.put("message", "intervention added succefully");

        return response;

    }


    @DeleteMapping("/interventions/delete")
    public Map<String, String> deleteIntervention(@RequestBody String[] body) {
        Map<String, String> response = new HashMap<>();
        String[] words = body[1].split(" "); // Split by whitespace
        Enseignant enseignant = new Enseignant();
        if (words.length >= 2) {
            String prenom = words[0];
            String nom = words[1];
            enseignant = enseignantRepository.findEnseignantByNomAndPrenom(nom,prenom);
        }
        Module module = moduleRepository.getModuleByIntitule(body[0]);

        if(interventionRepository.existsByEnseignant_IdAndModule_Id(enseignant.getId() , module.getId())) {
            Intervention intervention = interventionRepository.findByEnseignant_IdAndModule_Id(enseignant.getId() , module.getId());
            interventionRepository.delete(intervention);
            response.put("message", "intervention deleted succefully");
        } else {
            response.put("message", "error occurred");
        }
        return response;
    }
}

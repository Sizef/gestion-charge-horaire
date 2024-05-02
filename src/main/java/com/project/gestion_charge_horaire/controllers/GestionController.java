package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GestionController {

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    FiliereRepository filiereRepository;

    @Autowired
    InterventionRepository interventionRepository;
    @Autowired
    private EnseignantService enseignantService;


    // create new objects
    @GetMapping("/create")
    public String createFiliere() {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("Enseignant");
        enseignant.setEmail("enseignant@email.com");
        enseignant.setPrenom("EnseignantPrenom");
        enseignantRepository.save(enseignant);

        Module module = new Module();
        module.setIntitule("Genie Logiciel");
        module.setVh_td(3);
        module.setVh_cours(20);
        module.setVh_tp(3);
        module.setEvaluation(2);
        moduleRepository.save(module);

        Optional<Filiere> filiereOptional = filiereRepository.findById("ISI");
        Filiere filiere = null;
        if (filiereOptional.isPresent()) {
            filiere = filiereOptional.get();
            filiereRepository.save(filiere);
        }

        module.setFiliere(filiere);
        moduleRepository.save(module);

        Intervention intervention = new Intervention();
        InterventionId interventionId = new InterventionId();
        interventionId.setEmail(enseignant.getEmail());  // Assuming you have getEmail() method in Enseignant
        interventionId.setIntitule(module.getIntitule());      // Assuming you have getId() method in Module
        intervention.setInterventionId(interventionId);
        intervention.setEnseignant(enseignant);
        intervention.setModule(module);
        intervention.setVh_cours_intervention(7);
        intervention.setEvaluation_inetervention(2);
        intervention.setVh_td_inetervention(2);
        intervention.setVh_tp_inetervention(2);
        interventionRepository.save(intervention);

        return "success";
    }

   // @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        Enseignant enseignant = enseignantService.findByEmail(loginRequest.getEmail());
//        if (enseignant != null && loginRequest.getPassword().equals(enseignant.getPassword())) {
//            String token = tokenProvider.createToken(enseignant.getEmail(), enseignant.getRole());
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

}

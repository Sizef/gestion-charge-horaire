package com.project.gestion_charge_horaire.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.FiliereService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200")
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
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @PostMapping("/filiere/create")
    public Filiere createFiliere(@RequestBody String[] body) {
        Filiere filiere = new Filiere();
        filiere.setNomFiliere(body[0]);
        System.out.println(body[0]);
        if(filiereRepository.existsByNomFiliere(filiere.getNomFiliere())) {
            System.out.println("Il exist déja un filière avec ce nom");
            filiere.setNomFiliere("error");
        } else {
            filiereRepository.save(filiere);
            System.out.println("Filière ajouté avec succès");
        }
        return filiere;
    }

    @PutMapping("/filiere/modifier")
    public Filiere modifierFiliere(@RequestBody String[] body) {
        String AncienneNomFiliere = body[0];
        String nomFiliere = body[1];
        System.out.println(AncienneNomFiliere);
        System.out.println("*****************");
        System.out.println(nomFiliere);
        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Filiere> optionalFiliere = filiereRepository.findByNomFiliere(AncienneNomFiliere);
        if(optionalFiliere.isPresent() && !nomFiliere.isEmpty()) {
            Filiere filiere = optionalFiliere.get();
            filiere.setNomFiliere(nomFiliere);
            filiereRepository.save(filiere);
            return filiere;
        } else {
            return null;
        }

    }

    @GetMapping("/filieres/infos")
    public List<FiliereInfos> getFilieres() {
        // tous les filières
        List<Filiere> filieres = filiereService.findAllFilieres();
        //list d'infos
        List<FiliereInfos> filiereInfosList = new ArrayList<>();

        for(Filiere filiere : filieres) {
            //filière infos
            FiliereInfos filiereInfos = new FiliereInfos();
            filiereInfos.setId(filiere.getId());
            filiereInfos.setNom(filiere.getNomFiliere());
            filiereInfos.setNbreModules(moduleService.nombreModulesByFiliere(filiere));
            filiereInfos.setNbreEnseignants(enseignantService.nombreEnseignantsByFiliere(filiere));
            filiereInfosList.add(filiereInfos);
        }
        return filiereInfosList;

    }


    @GetMapping("/filiere")
    public List<Filiere> getAllFilieres() {
       if (filiereService.findAllFilieres().isEmpty())
           return null;
       else
           return filiereService.findAllFilieres();

    }

    @DeleteMapping("/filiere/delete")
    @Transactional
    public Map<String, String> deleteFiliere(@RequestBody String nomFiliere) {
        Map<String, String> response = new HashMap<>();
        Optional<Filiere> optionalFiliere = filiereRepository.findByNomFiliere(nomFiliere);
        if(optionalFiliere.isPresent()) {
            Filiere filiere = optionalFiliere.get();
            if(filiereService.deleteFiliere(filiere.getId())) {
                response.put("message", "Filiere deleted successfully");
            } else {
                response.put("message", "Filiere not found");
            }
        }
        return response;
    }

    // get enseignants for module
    @PostMapping("/filiere/enseignants_pour_module")
    public Map<String , Enseignant> getEnseignantsPourModule(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.getModulesByFiliere(filiere);
        List<Enseignant> enseignants = new ArrayList<>();
        Map<String , Enseignant> enseignantsPourModule = new HashMap<>();
        for (Module module : modules) {
            for (Intervention intervention : module.getInterventions()) {
                enseignantsPourModule.put(module.getIntitule(),intervention.getEnseignant());
            }
        }
        return enseignantsPourModule;
    }





}

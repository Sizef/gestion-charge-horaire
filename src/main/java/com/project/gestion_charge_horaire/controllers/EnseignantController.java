package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.outils.JwtTokenUtil;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;
    @Autowired
    private EnseignantRepository enseignantRepository;


    @PostMapping("/login")
    public Enseignant login(@RequestBody String[] body) {
        Enseignant enseignant = new Enseignant();
        String email = body[0];
        String password = body[1];
        System.out.println(password);
        for (Enseignant e : enseignantService.findAll()) {
            String email2 = e.getEmail();
            String password2 = e.getPassword();
            //System.out.println(password2);
            boolean AreEqual = (email2.equals(email));
            boolean AreEqual2 = (password2.equals(password));
            //System.out.println(AreEqual2);
            if (AreEqual && AreEqual2) {
                enseignant.setEmail(e.getEmail());
                enseignant.setNom(e.getNom());
                enseignant.setPrenom(e.getPrenom());
                enseignant.setPassword(e.getPassword());
            }
        }
        System.out.println(enseignant.getPassword());
        return enseignant;

    }

    @PostMapping("/enseignant/infos")
    public Enseignant getEnseignants(@RequestBody String email) {
        return enseignantService.enseignantInfos(email);
    }

    @GetMapping("/enseignants/roles")
    public List<Enseignant> getEnseignants() {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        List<Enseignant> enseignantList = new ArrayList<>();
        for (Enseignant e : enseignants) {
            if(e.getRoles().isEmpty()) {
                List<Role> roles = new ArrayList<>();
                Role role = new Role();
                role.setNom("Aucun role");
                roles.add(role);
                e.setRoles(roles);
            }
            enseignantList.add(e);
        }
        return enseignantList;
    }


    // get all enseignants
    @GetMapping("/enseigants")
    public String getAllEnseignants(){
        return enseignantService.getEnseignants();
    }


    // get enseignants by filiere
    @PostMapping("/filiere/enseignants")
    public List<Enseignant> findEnseignantsByFiliere(@RequestBody Filiere filiere) {
        List<Module> modules = moduleService.findModulesByFiliere(filiere);
        List<Intervention> interventions = new ArrayList<>();
        for (Module module : modules) {
            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
        }
        List<Enseignant> enseignants = new ArrayList<>();
        for (Intervention intervention : interventions) {
            enseignants.add(intervention.getEnseignant());
        }
        return enseignants;
    }

    // get nbre enseignants by filiere
    @PostMapping("/filiere/enseignants/nbre")
    public int findNbreEnseignantsByFiliere(@RequestBody Filiere filiere) {
        return enseignantService.nombreEnseignantsByFiliere(filiere);
    }

}

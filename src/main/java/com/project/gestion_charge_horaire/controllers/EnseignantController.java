package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.*;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.RoleRepository;
import com.project.gestion_charge_horaire.services.EnseignantService;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InterventionRepository interventionRepository;


    @PostMapping("/login")
    public Enseignant login(@RequestBody String[] body) {
        String email = body[0];
        String password = body[1];
        return enseignantService.findByEmailAndPassword(email , password);
    }

    @PostMapping("/enseignant/infos")
    public Enseignant getEnseignant(@RequestBody String email) {
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
    @PostMapping("/enseignant/create")
    public Map<String, String> createEnseignant(@RequestBody Enseignant enseignant) {
        System.err.println(enseignant.getEmail());
        Map<String, String> response = new HashMap<>();
        if(enseignantRepository.existsByEmail(enseignant.getEmail())) {
            response.put("message", "enseignant exist");
        } else {
            Enseignant en = new Enseignant();
            en.setEmail(enseignant.getEmail());
            en.setNom(enseignant.getNom());
            en.setPrenom(enseignant.getPrenom());
            enseignantRepository.save(en);

            Enseignant enseignant1 = enseignantRepository.findTopByOrderByIdDesc();
            System.err.println(enseignant1.getEmail());

            Role role = new Role();
            role.setNom(enseignant.getRoles().get(0).getNom());
            role.setEnseignant(enseignant1);
            roleRepository.save(role);
            response.put("message", "Enseignant added successfully");
        }
        return response;
    }


    @DeleteMapping("/enseignant/delete")
    @Transactional
    public Map<String, String> deleteEnseignant(@RequestBody int id) {
        Map<String, String> response = new HashMap<>();
        if(enseignantRepository.existsById((long) id)) {
            if(interventionRepository.existsByEnseignant_Id((long) id)) {
                interventionRepository.deleteInterventionsByEnseignant_Id((long) id);
            }
            if(roleRepository.existsByEnseignant_Id((long) id)) {
                roleRepository.deleteRolesByEnseignant_Id((long) id);
            }
            enseignantRepository.deleteById((long) id);
            response.put("message", "Enseignant deleted successfully");
        } else
            response.put("message", "Enseignant not found");
        return response;

    }

    @PutMapping("/enseignant/edit")
    public Enseignant editEnseignant(@RequestBody Enseignant enseignant) {
        Optional<Enseignant> OptEnseignant = enseignantRepository.findById(enseignant.getId());
        if (OptEnseignant.isPresent()) {
            Enseignant enseignant1 = OptEnseignant.get();
            enseignant1.setEmail(enseignant.getEmail());
            enseignant1.setNom(enseignant.getNom());
            enseignant1.setPrenom(enseignant.getPrenom());
            enseignantRepository.save(enseignant1);

            if(roleRepository.existsByEnseignant_Id(enseignant.getId())) {
                Role role = roleRepository.findByEnseignant_Id(enseignant.getId());
                role.setNom(enseignant.getRoles().get(0).getNom());
                roleRepository.save(role);
            } else {
                Role role = new Role();
                role.setNom(enseignant.getRoles().get(0).getNom());
                role.setEnseignant(enseignant1);
                roleRepository.save(role);
            }
            
            return enseignant;
        }
        return null;
    }

//    // get enseignants by filiere
//    @PostMapping("/filiere/enseignants")
//    public List<Enseignant> findEnseignantsByFiliere(@RequestBody Filiere filiere) {
//        List<Module> modules = moduleService.getModulesByFiliere(filiere);
//        List<Intervention> interventions = new ArrayList<>();
//        for (Module module : modules) {
//            interventions.addAll(interventionService.findInterventionByModuleIntitule(module.getIntitule()));
//        }
//        List<Enseignant> enseignants = new ArrayList<>();
//        for (Intervention intervention : interventions) {
//            enseignants.add(intervention.getEnseignant());
//        }
//        return enseignants;
//    }

    // get nbre enseignants by filiere
    @GetMapping("/enseignant/roles")
    public List<Role> findNbreEnseignantsByFiliere() {
        return roleRepository.findAll();
    }

}

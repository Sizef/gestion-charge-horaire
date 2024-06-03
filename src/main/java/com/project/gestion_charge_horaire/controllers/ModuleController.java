package com.project.gestion_charge_horaire.controllers;

import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import com.project.gestion_charge_horaire.models.Module_interventions;
import com.project.gestion_charge_horaire.outils.ModuleDetails;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import com.project.gestion_charge_horaire.services.InterventionService;
import com.project.gestion_charge_horaire.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private InterventionService interventionService;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private InterventionRepository interventionRepository;

    // get all modules
    @GetMapping("/module/infos")
    public List<ModuleDetails> getAllModules() {
        List<Module> modules = moduleRepository.findAll();
        List<ModuleDetails> moduleDetails = new ArrayList<>();
        for (Module module : modules) {
            ModuleDetails moduleDetail = new ModuleDetails();
            moduleDetail.setId(Math.toIntExact(module.getId()));
            moduleDetail.setIntitule(module.getIntitule());
            moduleDetail.setNomFiliere(module.filiere.getNomFiliere());
            moduleDetail.setEvaluation(module.getEvaluation());
            moduleDetail.setVh_tp(module.getVh_tp());
            moduleDetail.setVh_td(module.getVh_td());
            moduleDetail.setVh_cours(module.getVh_cours());
            moduleDetails.add(moduleDetail);
        }
        return moduleDetails;
    }


    @PostMapping("/module/create")
    public Module createFiliere(@RequestBody String[] body) {
        Module module = new Module();
        System.err.println(Arrays.toString(body));
        module.setIntitule(body[0]);
        module.setVh_cours(Integer.parseInt(body[1]));
        module.setVh_tp(Integer.parseInt(body[2]));
        module.setVh_td(Integer.parseInt(body[3]));
        module.setEvaluation(Integer.parseInt(body[4]));
        if(moduleRepository.existsModuleByIntitule(body[0])) {
            System.out.println("Il exist déja un module avec ce nom");
            module.setIntitule("error");
        } else {
            Optional<Filiere> Optfiliere = filiereRepository.findById(Long.valueOf(body[5]));
            if (Optfiliere.isPresent()) {
                Filiere filiere = Optfiliere.get();
                module.setFiliere(filiere);
                moduleRepository.save(module);
                System.out.println("Module ajouté avec succès");
            }
        }
        return module;
    }


    @PutMapping("/module/modifier")
    public Map<String, String> modifierFiliere(@RequestBody String[] body) {
        System.err.println(Arrays.toString(body));
        Map<String, String> response = new HashMap<>();
        Optional<Module> Optmodule = moduleRepository.findById(Long.valueOf(body[0]));
        if(Optmodule.isPresent()) {
            Module module = Optmodule.get();
            module.setId(Long.valueOf(body[0]));
            module.setIntitule(body[1]);
            module.setVh_cours(Integer.parseInt(body[2]));
            module.setVh_tp(Integer.parseInt(body[3]));
            module.setVh_td(Integer.parseInt(body[4]));
            module.setEvaluation(Integer.parseInt(body[5]));
            Optional<Filiere> Optfiliere = filiereRepository.findByNomFiliere(body[6]);
            if (Optfiliere.isPresent()) {
                Filiere filiere = Optfiliere.get();
                module.setFiliere(filiere);
                moduleRepository.save(module);
                System.out.println("Module modifier avec succès");
                response.put("message", "Module modifier avec succès");
            }
        } else {
            System.out.println("Il n'exist pas un module avec ce nom");
            response.put("message", "error");

        }
        return response;
    }


    @PostMapping("/module/interventions")
    public Module_interventions getInterventionsPourModule(@RequestBody Map<String, Object> body) {
        String moduleId = (String) body.get("moduleId");
        System.err.println(moduleId);
        Module_interventions module_interventions = new Module_interventions();
        int sum_cours = 0, sum_tps = 0, sum_tds = 0, sum_evaluation = 0;
        for(Intervention intervention : interventionRepository.findInterventionsByModule_Id(Long.valueOf(moduleId))) {
            sum_evaluation = sum_evaluation + intervention.getEvaluation_inetervention();
            sum_cours = sum_cours + intervention.getVh_cours_intervention();
            sum_tds = sum_tds + intervention.getVh_td_inetervention();
            sum_tps = sum_tps + intervention.getVh_tp_inetervention();
        }

        Optional<Module> Optmodule = moduleRepository.findById(Long.valueOf(moduleId));
        if(Optmodule.isPresent()) {
            Module module = Optmodule.get();
            module_interventions.setVh_cours_intervention(module.getVh_cours() - sum_cours);
            module_interventions.setEvaluation_inetervention(module.getEvaluation() - sum_evaluation);
            module_interventions.setVh_td_inetervention(module.getVh_td() - sum_tds);
            module_interventions.setVh_tp_inetervention(module.getVh_tp() - sum_tps);
        }

        return module_interventions;
    }



    // get modues by filiere
    @PostMapping("/filiere/modules")
    public List<Module> findModulesByFiliere(@RequestBody Filiere filiere) {
        return moduleService.getModulesByFiliere(filiere);
    }

   @DeleteMapping("module/delete")
    public Map<String, String> deleteModule(@RequestBody int id) {
       Map<String, String> response = new HashMap<>();
       if(moduleRepository.existsById((long) id)) {
           if(interventionRepository.existsByModule_Id((long) id)) {
               interventionRepository.deleteInterventionsByModule_Id((long) id);
           }
           moduleRepository.deleteById((long) id);
           response.put("message", "Module deleted successfully");
       } else
           response.put("message", "Module not found");
       return response;
   }
}

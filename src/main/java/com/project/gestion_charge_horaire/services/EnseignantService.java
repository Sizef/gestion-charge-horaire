package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    EnseignantRepository enseignantRepository;

    public String getEnseignants() {
        if(enseignantRepository.findAll().toArray().length == 0){
            return "There is no Enseignants in the database";
        }else {
            return "There are " + enseignantRepository.findAll().toArray().length + " Enseignants";
        }
    }
}

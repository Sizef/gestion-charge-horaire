package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {

    @Autowired
    FiliereRepository filiereRepository;

    public List<Filiere> findAllFilieres() {
        return filiereRepository.findAll();
    }




}

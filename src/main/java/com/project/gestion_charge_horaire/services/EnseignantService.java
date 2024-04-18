package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService {

    @Autowired
    EnseignantRepository enseignantRepository;
}

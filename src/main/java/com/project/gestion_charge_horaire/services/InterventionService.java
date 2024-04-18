package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.repositories.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {

    @Autowired
    InterventionRepository interventionRepository;
}

package com.project.gestion_charge_horaire.services;

import com.project.gestion_charge_horaire.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;
}

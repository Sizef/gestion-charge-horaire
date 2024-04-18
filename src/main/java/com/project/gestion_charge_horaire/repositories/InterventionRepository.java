package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, String> {
}

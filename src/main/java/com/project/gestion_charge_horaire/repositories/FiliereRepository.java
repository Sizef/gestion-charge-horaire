package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, String> {
}

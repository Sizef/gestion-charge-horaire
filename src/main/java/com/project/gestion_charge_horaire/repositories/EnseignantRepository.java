package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    Enseignant findByEmail(String email);

    Enseignant findByEmailAndPassword(String email , String password);

    Long findIdByEmail(String email);

    Enseignant findTopByOrderByIdDesc();

    int countDistinctByInterventions(List<Intervention> interventions);

    boolean existsByEmail(String email);

    List<Enseignant> findEnseignantByInterventions_Empty();

    Enseignant findEnseignantsByInterventions(List<Intervention> interventions);


}

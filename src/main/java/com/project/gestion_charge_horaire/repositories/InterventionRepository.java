package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, String> {

    List<Intervention> findInterventionByModuleIntitule(String intitule);

    List<Intervention> findInterventionByEnseignant(Enseignant enseignant);

    List<Intervention> findInterventionByModule(Module module);

}

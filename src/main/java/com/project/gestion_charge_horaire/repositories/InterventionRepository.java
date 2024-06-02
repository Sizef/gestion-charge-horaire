package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.InterventionId;
import com.project.gestion_charge_horaire.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, InterventionId> {

    List<Intervention> findInterventionByModuleIntitule(String intitule);

    List<Intervention> findInterventionByEnseignant(Enseignant enseignant);

    List<Intervention> findInterventionsByModule(Module module);

    void deleteInterventionByModule(Module module);

    boolean existsByModule_Id(Long module_id);

    boolean existsByEnseignant_Id(Long enseignant_id);

    void deleteInterventionsByModule_Id(Long module_id);

    void deleteInterventionsByEnseignant_Id(Long enseignant_id);
}

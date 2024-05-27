package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
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

    public Enseignant findByEmailAndInterventionsNot(String email, List<Intervention> interventions);

    Enseignant findByEmail(String email);

    Enseignant findByEmailAndPassword(String email , String password);

    //public List<Enseignant> findAllEnseignantsWithRoles();

    public int countDistinctByInterventions(List<Intervention> interventions);


    public List<Enseignant> findEnseignantByInterventions_Empty();

    public Enseignant findEnseignantsByInterventions(List<Intervention> interventions);


}

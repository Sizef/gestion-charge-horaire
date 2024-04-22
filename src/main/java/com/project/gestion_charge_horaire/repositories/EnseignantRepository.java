package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, String> {

    public Enseignant findByEmailAndInterventionsNot(String email, List<Intervention> interventions);

    //public int countEnseignantBy(String email);

    public List<Enseignant> findEnseignantByInterventions_Empty();

    public Enseignant findEnseignantsByInterventions(List<Intervention> interventions);


}

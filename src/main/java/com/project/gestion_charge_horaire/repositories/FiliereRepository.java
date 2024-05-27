package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Enseignant;
import com.project.gestion_charge_horaire.models.Filiere;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    @NonNull
    @Override
    public List<Filiere> findAll();

    public boolean existsByNomFiliere(String nomFiliere);

    public Optional<Filiere> findByNomFiliere(String nomFiliere);





}

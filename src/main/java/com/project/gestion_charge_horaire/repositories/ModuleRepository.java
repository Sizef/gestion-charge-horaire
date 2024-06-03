package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Filiere;
import com.project.gestion_charge_horaire.models.Intervention;
import com.project.gestion_charge_horaire.models.Module;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> getModulesByFiliere(Filiere filiere);

    int countModulesByFiliere(@NonNull Filiere filiere);

    boolean existsModuleByIntitule(@NonNull String intitule);

    Module getModuleByIntitule(@NonNull String intitule);



}

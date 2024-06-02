package com.project.gestion_charge_horaire.repositories;

import com.project.gestion_charge_horaire.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByEnseignant_Id(Long enseignant_id);

    Role findByEnseignant_Id(Long enseignant_id);

    void deleteRolesByEnseignant_Id(Long enseignant_id);
}

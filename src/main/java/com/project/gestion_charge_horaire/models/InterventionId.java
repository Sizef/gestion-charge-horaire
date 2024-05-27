package com.project.gestion_charge_horaire.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class InterventionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="id_module")
    public Long id_module;

    @Column(name="id_enseignant")
    public Long id_enseignant;


}

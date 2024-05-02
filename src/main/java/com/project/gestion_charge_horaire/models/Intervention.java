package com.project.gestion_charge_horaire.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Intervention {

    @EmbeddedId
    private InterventionId interventionId;
    private int vh_cours_intervention;
    private int vh_td_inetervention;
    private int vh_tp_inetervention;
    private int evaluation_inetervention;

    @ManyToOne
    @MapsId("intitule")
    @JoinColumn(name="intitule")
    public Module module;

    @ManyToOne
    @MapsId("email")
    @JoinColumn(name="email")
    public Enseignant enseignant;

}

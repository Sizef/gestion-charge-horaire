package com.project.gestion_charge_horaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class GestionChargeHoraireApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionChargeHoraireApplication.class, args);
    }

}

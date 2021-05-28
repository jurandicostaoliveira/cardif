package com.cardif.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "cargo")
@SuppressWarnings("serial")
public class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cargoId;

    @Column(length = 50)
    private String cargoName;

    public Cargo(Long cargoId, String cargoName) {
        this.cargoId = cargoId;
        this.cargoName = cargoName;
    }

    public Cargo(){}

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
}

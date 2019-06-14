package br.com.crud.vehicles.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 4221129248300873063L;

    private @Id @GeneratedValue Long id;

    private String name;
    private String desc;
    private Long vehicleType;
    private String plate;

    public Vehicle(String name, String desc, Long vehicleType, String plate) {
        this.name = name;
        this.desc = desc;
        this.vehicleType = vehicleType;
        this.plate = plate;
    }
}

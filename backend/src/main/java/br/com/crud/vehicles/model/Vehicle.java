package br.com.crud.vehicles.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 4221129248300873063L;

    private @Id
    @GeneratedValue
    Long id;

    private String name;
    private String desc;
    private Long idVehicleType;
    private String plate;

    @ManyToOne
    @JoinColumn(name = "idVehicleType", insertable = false, updatable = false)
    private VehicleType vehicleType;

    public Vehicle(String name, String desc, Long idVehicleType, String plate) {
        this.name = name;
        this.desc = desc;
        this.idVehicleType = idVehicleType;
        this.plate = plate;
    }
}

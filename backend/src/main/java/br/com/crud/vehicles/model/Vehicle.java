package br.com.crud.vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 4221129248300873063L;

    private Long id;

    private String name;
    private String desc;
    private Long idVehicleType;
    private String plate;

    private VehicleType vehicleType;

    public Vehicle(Long id, String name, String desc, Long idVehicleType, String plate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.idVehicleType = idVehicleType;
        this.plate = plate;
    }
}

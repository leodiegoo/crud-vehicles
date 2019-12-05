package br.com.crud.vehicles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType implements Serializable {
    private static final long serialVersionUID = 3471298425165089856L;

    private Long id;
    private String name;
    private String desc;
}

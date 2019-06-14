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
public class VehicleType implements Serializable {
    private static final long serialVersionUID = 3471298425165089856L;

    private @Id @GeneratedValue Long id;
    private String name;
    private String desc;
}

package br.com.crud.vehicles.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {

    public static final int BOOLEAN_TRUE = 1;
    public static final int BOOLEAN_FALSE = 0;

    private Object model;
    private String message;
    private Integer success = BOOLEAN_FALSE;
}

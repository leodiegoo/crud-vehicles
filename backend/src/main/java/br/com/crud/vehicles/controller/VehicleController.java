package br.com.crud.vehicles.controller;

import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> buscarTodos(){
        try{
            return ResponseEntity.ok(vehicleService.buscarTodos());
        }catch(Exception e){
            log.error("Ocorreu um erro ao buscar o veículo", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> buscarPorId(@PathVariable Long id) {
        try {
            Vehicle vehicle = vehicleService.buscarPorId(id);
            if (vehicle != null) {
                return ResponseEntity.ok(vehicle);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Ocorreu um erro ao buscar o veículo", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

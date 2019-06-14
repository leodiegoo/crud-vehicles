package br.com.crud.vehicles.controller;

import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.service.CrudService;
import br.com.crud.vehicles.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicles")
public class VehicleController extends CrudController<Vehicle, Long> {

    private final VehicleService vehicleService;

    @Override
    protected CrudService<Vehicle, Long> getService() {
        return vehicleService;
    }

}

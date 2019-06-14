package br.com.crud.vehicles.controller;

import br.com.crud.vehicles.model.VehicleType;
import br.com.crud.vehicles.service.CrudService;
import br.com.crud.vehicles.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicleTypes")
public class VehicleTypeController extends CrudController<VehicleType, Long> {

    private final VehicleTypeService vehicleTypeService;

    @Override
    protected CrudService<VehicleType, Long> getService() {
        return vehicleTypeService;
    }
}

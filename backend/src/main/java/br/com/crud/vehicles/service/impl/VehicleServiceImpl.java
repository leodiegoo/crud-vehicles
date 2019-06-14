package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.repository.VehicleRepository;
import br.com.crud.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle, Long> implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    protected JpaRepository<Vehicle, Long> getRepository() {
        return vehicleRepository;
    }
}

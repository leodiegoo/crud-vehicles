package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.repository.VehicleRepository;
import br.com.crud.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle, Long> implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    protected JpaRepository<Vehicle, Long> getRepository() {
        return vehicleRepository;
    }

    @Override
    public Vehicle put(Long id, Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (!vehicleOptional.isPresent()) {
            return null;
        }
        if (!StringUtils.isEmpty(vehicle.getDesc())) {
            vehicleOptional.get().setDesc(vehicle.getDesc());
        }
        if (!StringUtils.isEmpty(vehicle.getName())) {
            vehicleOptional.get().setName(vehicle.getName());
        }
        if (!StringUtils.isEmpty(vehicle.getPlate())) {
            vehicleOptional.get().setPlate(vehicle.getPlate());
        }
        if (vehicle.getIdVehicleType() != null) {
            vehicleOptional.get().setIdVehicleType(vehicle.getIdVehicleType());
        }
        return vehicleRepository.save(vehicleOptional.get());
    }
}

package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.repository.JOOQRepository;
import br.com.crud.vehicles.repository.VehicleRepository;
import br.com.crud.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle, Long> implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    protected JOOQRepository<Vehicle, Long> getRepository() {
        return vehicleRepository;
    }

    @Override
    public Vehicle put(Long id, Vehicle vehicle) {
        Vehicle vehicleDb = getRepository().findById(id);
        if (vehicleDb == null) {
            return null;
        }
        if (!StringUtils.isEmpty(vehicle.getDesc())) {
            vehicleDb.setDesc(vehicle.getDesc());
        }
        if (!StringUtils.isEmpty(vehicle.getName())) {
            vehicleDb.setName(vehicle.getName());
        }
        if (!StringUtils.isEmpty(vehicle.getPlate())) {
            vehicleDb.setPlate(vehicle.getPlate());
        }
        if (vehicle.getIdVehicleType() != null) {
            vehicleDb.setIdVehicleType(vehicle.getIdVehicleType());
        }
        return getRepository().update(vehicleDb);
    }
}

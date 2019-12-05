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
        if (!StringUtils.isEmpty(vehicleDb.getDesc())) {
            vehicleDb.setDesc(vehicleDb.getDesc());
        }
        if (!StringUtils.isEmpty(vehicleDb.getName())) {
            vehicleDb.setName(vehicleDb.getName());
        }
        if (!StringUtils.isEmpty(vehicleDb.getPlate())) {
            vehicleDb.setPlate(vehicleDb.getPlate());
        }
        if (vehicleDb.getIdVehicleType() != null) {
            vehicleDb.setIdVehicleType(vehicleDb.getIdVehicleType());
        }
        return getRepository().update(vehicleDb);
    }
}

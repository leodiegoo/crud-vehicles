package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.VehicleType;
import br.com.crud.vehicles.repository.VehicleTypeRepository;
import br.com.crud.vehicles.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeServiceImpl extends CrudServiceImpl<VehicleType, Long> implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleType put(Long id, VehicleType entidade) {
        return null;
    }

    @Override
    protected JpaRepository<VehicleType, Long> getRepository() {
        return vehicleTypeRepository;
    }
}

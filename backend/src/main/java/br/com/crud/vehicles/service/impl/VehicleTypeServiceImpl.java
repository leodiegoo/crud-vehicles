package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.VehicleType;
import br.com.crud.vehicles.repository.JOOQRepository;
import br.com.crud.vehicles.repository.VehicleTypeRepository;
import br.com.crud.vehicles.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VehicleTypeServiceImpl extends CrudServiceImpl<VehicleType, Long> implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    protected JOOQRepository<VehicleType, Long> getRepository() {
        return vehicleTypeRepository;
    }

    @Override
    public VehicleType put(Long id, VehicleType entidade) {
        VehicleType vehicleTypeDb = getRepository().findById(id);
        if (vehicleTypeDb == null) {
            return null;
        }
        if (!StringUtils.isEmpty(entidade.getDesc())) {
            vehicleTypeDb.setDesc(entidade.getDesc());
        }
        if (!StringUtils.isEmpty(entidade.getName())) {
            vehicleTypeDb.setName(entidade.getName());
        }
        return getRepository().update(vehicleTypeDb);
    }
}

package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.model.VehicleType;
import br.com.crud.vehicles.repository.VehicleTypeRepository;
import br.com.crud.vehicles.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class VehicleTypeServiceImpl extends CrudServiceImpl<VehicleType, Long> implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleType put(Long id, VehicleType entidade) {
        Optional<VehicleType> vehicleTypeOptional = vehicleTypeRepository.findById(id);
        if (!vehicleTypeOptional.isPresent()) {
            return null;
        }
        if (!StringUtils.isEmpty(entidade.getDesc())) {
            vehicleTypeOptional.get().setDesc(entidade.getDesc());
        }
        if (!StringUtils.isEmpty(entidade.getName())) {
            vehicleTypeOptional.get().setName(entidade.getName());
        }
        return vehicleTypeRepository.save(vehicleTypeOptional.get());
    }

    @Override
    protected JpaRepository<VehicleType, Long> getRepository() {
        return vehicleTypeRepository;
    }
}

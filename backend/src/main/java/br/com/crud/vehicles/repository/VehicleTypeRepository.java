package br.com.crud.vehicles.repository;

import br.com.crud.vehicles.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}

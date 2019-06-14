package br.com.crud.vehicles.repository;


import br.com.crud.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

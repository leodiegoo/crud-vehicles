package br.com.crud.vehicles.repository.impl;

import br.com.crud.vehicles.db.tables.records.VehiclesRecord;
import br.com.crud.vehicles.model.Vehicle;
import br.com.crud.vehicles.repository.VehicleRepository;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.com.crud.vehicles.db.tables.Vehicles.VEHICLES;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRepositoryImpl.class);

    @Autowired
    private DSLContext jooq;

    @Transactional
    @Override
    public Vehicle add(Vehicle vehicleEntry) {
        jooq.insertInto(VEHICLES)
                .set(createRecord(vehicleEntry))
                .execute();

        Vehicle returned = findById(jooq.lastID().longValue());

        return returned;
    }

    private VehiclesRecord createRecord(Vehicle vehicleEntry) {
        VehiclesRecord record = new VehiclesRecord();

        record.setDesc(vehicleEntry.getDesc());
        record.setName(vehicleEntry.getName());
        record.setPlate(vehicleEntry.getPlate());
        record.setIdVehicleType(vehicleEntry.getIdVehicleType());

        return record;
    }

    private Vehicle convertQueryResultToModelObject(VehiclesRecord queryResult) {
        return new Vehicle(queryResult.getId(), queryResult.getName(), queryResult.getDesc(), queryResult.getIdVehicleType(), queryResult.getPlate());
    }

    private List<Vehicle> convertQueryResultsToModelObjects(List<VehiclesRecord> queryResults) {
        List<Vehicle> vehicleEntries = new ArrayList<>();

        for (VehiclesRecord queryResult : queryResults) {
            Vehicle vehicleEntry = convertQueryResultToModelObject(queryResult);
            vehicleEntries.add(vehicleEntry);
        }

        return vehicleEntries;
    }

    @Transactional
    @Override
    public Vehicle update(Vehicle vehicleEntry) {

        jooq.update(VEHICLES)
                .set(VEHICLES.DESC, vehicleEntry.getDesc())
                .set(VEHICLES.NAME, vehicleEntry.getName())
                .set(VEHICLES.PLATE, vehicleEntry.getPlate())
                .set(VEHICLES.ID_VEHICLE_TYPE, vehicleEntry.getIdVehicleType())
                .where(VEHICLES.ID.equal(vehicleEntry.getId()))
                .execute();

        return findById(vehicleEntry.getId());
    }

    @Transactional
    @Override
    public Vehicle delete(Long id) {
        Vehicle deleted = findById(id);

        jooq.delete(VEHICLES)
                .where(VEHICLES.ID.equal(id))
                .execute();

        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Vehicle> findAll() {
        List<VehiclesRecord> queryResults = jooq.selectFrom(VEHICLES).fetchInto(VehiclesRecord.class);

        List<Vehicle> vehicleEntries = convertQueryResultsToModelObjects(queryResults);

        return vehicleEntries;
    }

    @Transactional(readOnly = true)
    @Override
    public Vehicle findById(Long id) {

        VehiclesRecord queryResult = jooq.selectFrom(VEHICLES)
                .where(VEHICLES.ID.equal(id))
                .fetchOne();

        if (queryResult == null) {
            throw new RuntimeException("Nenhum veículo encontrado com o ID: " + id);
        }

        return convertQueryResultToModelObject(queryResult);
    }
}

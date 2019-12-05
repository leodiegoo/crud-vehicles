package br.com.crud.vehicles.repository.impl;

import br.com.crud.vehicles.db.tables.records.VehiclesTypesRecord;
import br.com.crud.vehicles.model.VehicleType;
import br.com.crud.vehicles.repository.VehicleTypeRepository;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.com.crud.vehicles.db.tables.VehiclesTypes.VEHICLES_TYPES;

@Repository
public class VehicleTypeRepositoryImpl implements VehicleTypeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleTypeRepositoryImpl.class);

    @Autowired
    private DSLContext jooq;

    @Transactional
    @Override
    public VehicleType add(VehicleType vehicleTypeEntry) {
        jooq.insertInto(VEHICLES_TYPES)
                .set(createRecord(vehicleTypeEntry))
                .execute();

        VehicleType returned = findById(jooq.lastID().longValue());

        return returned;
    }

    private VehiclesTypesRecord createRecord(VehicleType vehicleTypeEntry) {
        VehiclesTypesRecord record = new VehiclesTypesRecord();

        record.setDesc(vehicleTypeEntry.getDesc());
        record.setName(vehicleTypeEntry.getName());

        return record;
    }

    private VehicleType convertQueryResultToModelObject(VehiclesTypesRecord queryResult) {
        return new VehicleType(queryResult.getId(), queryResult.getName(), queryResult.getDesc());
    }

    private List<VehicleType> convertQueryResultsToModelObjects(List<VehiclesTypesRecord> queryResults) {
        List<VehicleType> vehicleTypeEntries = new ArrayList<>();

        for (VehiclesTypesRecord queryResult : queryResults) {
            VehicleType vehicleTypeEntry = convertQueryResultToModelObject(queryResult);
            vehicleTypeEntries.add(vehicleTypeEntry);
        }

        return vehicleTypeEntries;
    }

    @Transactional
    @Override
    public VehicleType update(VehicleType vehicleTypeEntry) {

        jooq.update(VEHICLES_TYPES)
                .set(VEHICLES_TYPES.DESC, vehicleTypeEntry.getDesc())
                .set(VEHICLES_TYPES.NAME, vehicleTypeEntry.getName())
                .where(VEHICLES_TYPES.ID.equal(vehicleTypeEntry.getId()))
                .execute();

        return findById(vehicleTypeEntry.getId());
    }

    @Transactional
    @Override
    public VehicleType delete(Long id) {
        VehicleType deleted = findById(id);

        jooq.delete(VEHICLES_TYPES)
                .where(VEHICLES_TYPES.ID.equal(id))
                .execute();

        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VehicleType> findAll() {
        List<VehiclesTypesRecord> queryResults = jooq.selectFrom(VEHICLES_TYPES).fetchInto(VehiclesTypesRecord.class);

        List<VehicleType> vehicleTypeEntries = convertQueryResultsToModelObjects(queryResults);

        return vehicleTypeEntries;
    }

    @Transactional(readOnly = true)
    @Override
    public VehicleType findById(Long id) {

        VehiclesTypesRecord queryResult = jooq.selectFrom(VEHICLES_TYPES)
                .where(VEHICLES_TYPES.ID.equal(id))
                .fetchOne();

        if (queryResult == null) {
            throw new RuntimeException("Nenhum tipo de veículo encontrado com o ID: " + id);
        }

        return convertQueryResultToModelObject(queryResult);
    }

}

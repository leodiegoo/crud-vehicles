package br.com.crud.vehicles.model;

import br.com.crud.vehicles.service.VehicleTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehicleTypeTest {

    private static Long ID = 1L;
    private static String NAME = "Type test";
    private static String DESC = "Desc test";

    @Autowired
    VehicleTypeService vehicleTypeService;

    @Test
    public void testVehicleTypeServiceCreateNewVehicleType() {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setDesc(DESC);
        vehicleType.setName(NAME);
        if (vehicleType != null) {
            assertThat(vehicleTypeService.salvar(vehicleType), instanceOf(VehicleType.class));
            assertNotNull("Name can not to be empty", vehicleType.getName());
            assertNotNull("Description can not to be empty", vehicleType.getDesc());
        }

        assertNotNull(vehicleType);
    }
}

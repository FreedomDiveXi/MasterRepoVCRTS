import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VehicleTest {

    @Test
    public void testCreateVehicleWithUniqueID() {
        Vehicle vehicle8 = new Vehicle("Adam", 8, "Toyota", "Camry", 2018);
        assertEquals(8, vehicle8.getVehicleId());
    }

    @Test
    public void testGetMake() {
        Vehicle vehicle = new Vehicle("Dave", 3, "Toyota", "Camry", 2020);
        assertEquals("Toyota", vehicle.getMake());
    }

    @Test
    public void testGetModel() {
        Vehicle vehicle = new Vehicle("Eve", 4, "Toyota", "Camry", 2021);
        assertEquals("Camry", vehicle.getModel());
    }

    @Test
    public void testGetYear() {
        Vehicle vehicle = new Vehicle("Frank", 5, "Toyota", "Camry", 2022);
        assertEquals(2022, vehicle.getYear());
    }

    @Test
    public void testVehicleConstructor() {
        Vehicle vehicle7 = new Vehicle("John", 1, "Toyota", "Camry", 2020);
        assertEquals("John", vehicle7.getVehicleOwner());
        assertEquals(1, vehicle7.getVehicleId());
        assertEquals("Toyota", vehicle7.getMake());
        assertEquals("Camry", vehicle7.getModel());
        assertEquals(2020, vehicle7.getYear());
        assertNull(vehicle7.getAssignedJob());

        Vehicle vehicle9 = new Vehicle("Jane", 2, "Toyota", "Avalon", 2024);
        assertEquals("Jane", vehicle9.getVehicleOwner());
        assertEquals(2, vehicle9.getVehicleId());
    }

    @Test
    public void testAssignJobToVehicle() {
        Job job = new Job("Doe", 101, 5);
        Vehicle vehicle1 = new Vehicle("Jeff", 6, "Toyota", "Corolla", 2023);
        vehicle1.setAssignedJob(job);
        assertEquals(job, vehicle1.getAssignedJob());

        vehicle1.removeAssignedJob();
        assertNull(vehicle1.getAssignedJob());
    }
}

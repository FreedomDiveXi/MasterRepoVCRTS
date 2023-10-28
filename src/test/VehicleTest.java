import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class VehicleTest {

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeEach
    public void setup() {
        vehicle1 = new Vehicle("John", 1, "Toyota", "Camry", 2020);
        vehicle2 = new Vehicle("Jane", 2, "Honda", "Civic", 2021);
    }

    @Test
    public void testVehicleConstructor() {
        assertEquals("John", vehicle1.getVehicleOwner());
        assertEquals(1, vehicle1.getVehicleId());
        assertEquals("Toyota", vehicle1.getMake());
        assertEquals("Camry", vehicle1.getModel());
        assertEquals(2020, vehicle1.getYear());
        assertNull(vehicle1.getAssignedJob());

        assertEquals("Jane", vehicle2.getVehicleOwner());
        assertEquals(2, vehicle2.getVehicleId());
    }
 @Test
    public void testDuplicateVehicleId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Vehicle vehicle3 = new Vehicle("Doe", 1, "Ford", "Mustang", 2022);
        });
    }

    @Test
    public void testAssignJobToVehicle() {
        Job job = new Job("Doe", 101, 5);
        vehicle1.setAssignedJob(job);
        assertEquals(job, vehicle1.getAssignedJob());

        vehicle1.removeAssignedJob();
        assertNull(vehicle1.getAssignedJob());
    }
}

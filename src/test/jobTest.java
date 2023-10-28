// JUnit test class
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class JobTest {

    private Job job;
    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeEach
    public void setup() {
        job = new Job("John", 101, 5);
        vehicle1 = new Vehicle("Jane", 3, "Toyota", "Corolla", 2020);
        vehicle2 = new Vehicle("Doe", 4, "Honda", "Accord", 2021);
    }

    @Test
    public void testJobConstructor() {
        assertEquals("John", job.getJobOwnerName());
        assertEquals(101, job.getJobID());
        assertEquals(5, job.getJobDurationTime());
        assertEquals(0, job.getJobExecutionTime());
        assertFalse(job.isJobCompletion());
        assertNull(job.getJobDeadline());
        assertTrue(job.getAssignedVehicles().isEmpty());
    }

    @Test
    public void testJobConstructorWithDeadline() {
        Job jobWithDeadline = new Job("Jane", 102, 3, "2023-12-31");
        assertEquals("Jane", jobWithDeadline.getJobOwnerName());
        assertEquals(102, jobWithDeadline.getJobID());
        assertEquals(3, jobWithDeadline.getJobDurationTime());
        assertEquals(0, jobWithDeadline.getJobExecutionTime());
        assertFalse(jobWithDeadline.isJobCompletion());
        assertEquals("2023-12-31", jobWithDeadline.getJobDeadline());
        assertTrue(jobWithDeadline.getAssignedVehicles().isEmpty());
    }

    @Test
    public void testSetJobCompletion() {
        job.setJobCompletion(true);
        assertTrue(job.isJobCompletion());
    }

    @Test
    public void testExecutionTime() {
        job.setExecutionTime(2);
        assertEquals(2, job.getJobExecutionTime());
        
        job.setExecutionTime(3);
        assertEquals(5, job.getJobExecutionTime());
    }

    @Test
    public void testAssignedVehicles() {
        assertTrue(job.getAssignedVehicles().isEmpty());

        job.addAssignedVehicle(vehicle1);
        job.addAssignedVehicle(vehicle2);
        ArrayList<Vehicle> assignedVehicles = job.getAssignedVehicles();

        assertEquals(2, assignedVehicles.size());
        assertTrue(assignedVehicles.contains(vehicle1));
        assertTrue(assignedVehicles.contains(vehicle2));

        job.removeAssignedVehicle(vehicle1);
        assertEquals(1, assignedVehicles.size());
        assertFalse(assignedVehicles.contains(vehicle1));
        assertTrue(assignedVehicles.contains(vehicle2));
    }
    
     @Test
    public void testAssignVehicleToJob() {
        assertTrue(job.getAssignedVehicles().isEmpty());

        job.addAssignedVehicle(vehicle1);
        job.addAssignedVehicle(vehicle2);

        assertEquals(2, job.getAssignedVehicles().size());
        assertTrue(job.getAssignedVehicles().contains(vehicle1));
        assertTrue(job.getAssignedVehicles().contains(vehicle2));
        assertEquals(job, vehicle1.getAssignedJob());
        assertEquals(job, vehicle2.getAssignedJob());

        job.removeAssignedVehicle(vehicle1);
        assertNull(vehicle1.getAssignedJob());
        assertEquals(1, job.getAssignedVehicles().size());
        assertTrue(job.getAssignedVehicles().contains(vehicle2));
    }
}

import org.junit.jupiter.api.Test;
import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CloudControllerTest {
    CloudController controller = new CloudController();

    // testing object creations
    @Test
    void createJobOwner() {
        JobOwner newJobOwner = controller.createJobOwner("Testing", "asdkjh123kjh123");
        assertEquals(newJobOwner.getUsername() ,"Testing");
    }

    @Test
    void createVehicleOwner() {
        VehicleOwner newVehicleOwner= controller.createVehicleOwner("Testing", "asdkjh123kjh123");
        assertEquals(newVehicleOwner.getUsername() ,"Testing");
    }

    @Test
    void createJob() {
        JobOwner newJobOwner = controller.createJobOwner("Testing", "asdflskj234123d");
        Job newJob = controller.createJob("Testing", "12345", "14", "10-23-19");
        assertEquals(newJob.getJobOwnerName(), "Testing");
        assertEquals(newJob.getJobID(), 12345);
        assertEquals(newJob.getJobDurationTime(), 14);
        assertEquals(newJob.getJobDeadline(), "10-23-19");
    }

    // tests to see if the job stored in the job owner is the same as the created job
    @Test
    void jobOwnerJobAssignation() {
        JobOwner newJobOwner = controller.createJobOwner("Testing", "asdflskj234123d");
        Job newJob= controller.createJob("Testing", "12345","14", "10-23-19");

        Job temp = newJobOwner.getOwnedJobs().remove(0);
        assertEquals(temp, newJob);
    }
    @Test
    void createVehicle() {
        Vehicle newVehicle= controller.createVehicle("Testing", "123", "Pilot", "Honda", "2023");
        assertEquals(newVehicle.getVehicleOwner(), "Testing");
        assertEquals(newVehicle.getVehicleId() ,123);
        assertEquals(newVehicle.getYear(), 2023);
    }

    // tests to see if the vehicle stored in the vehicle owner vehicle list is the same as the created vehicle
    @Test
    void vehicleOwnerVehicleAssignation() {
        VehicleOwner newVehicleOwner= controller.createVehicleOwner("Testing", "asdflskj234123d");
        Vehicle newVehicle= controller.createVehicle("Testing", "12345","Pilot", "Honda", "2023");

        Vehicle temp = newVehicleOwner.getVehicleList().remove(0);
        assertEquals(temp, newVehicle);
    }

    @Test
    void generateRedundancy(){
        ArrayList<Integer> ones = new ArrayList<>();
        ArrayList<Integer> twos = new ArrayList<>();

        while(ones.size() < 5 || twos.size() < 5){
            int temp = controller.generateRedundancy();

            if(temp == 2 && twos.size() < 5)
                twos.add(temp);

            if(temp == 1 && ones.size() < 5)
                ones.add(temp);
        }
        assertEquals(ones.toString(), "[1, 1, 1, 1, 1]");
        assertEquals(twos.toString(), "[2, 2, 2, 2, 2]");
    }

    @Test
    void startProcessing() {
    }
}

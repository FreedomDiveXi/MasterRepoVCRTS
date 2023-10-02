import java.io.FileWriter;
import java.io.IOException;
import users.vehicleOwner.Vehicle;


public class Reporter {

    private static final String FILE_PATH = "report.txt";

    public void reportVehicle(Vehicle vehicle) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) { // true means we append to the file
            writer.write("Vehicle Report:\n");
            writer.write("Model: " + vehicle.getVehicleModel() + "\n");
            writer.write("Make: " + vehicle.getVehicleMake() + "\n");
            writer.write("Year: " + vehicle.getVehicleYear() + "\n");
            writer.write("In Use: " + vehicle.isInUse() + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reportJob(Job job) {
        // Similar approach as `reportVehicle` to write details about the job to the file
    }

    public static void main(String[] args) {
        Vehicle car = new Vehicle("Model S", "Tesla", 2020);
        Reporter reporter = new Reporter();
        reporter.reportVehicle(car);
    }
}


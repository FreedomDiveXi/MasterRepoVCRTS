import java.util.ArrayList;
import java.util.stream.Collectors;

import users.jobOwner.Job;
import users.vehicleOwner.Vehicle;

public class Dispatcher {
  private ArrayList <Vehicle> globalVehicleList;
  private ArrayList <Job> globalJobList;

  /**
   * Method is overloaded, based on what the user passes into the method, 
   * will choose the appriate method to call based on the input passed.
   * will append the input to respected global list. 
   */
  public ArrayList<Job> addToGlobalList(Job job){
    globalJobList.add(job);
    return this.globalJobList;
  }
  public ArrayList<Vehicle> addToGlobalList(Vehicle vehicle){
    globalVehicleList.add(vehicle);
    return this.globalVehicleList;
  }

  public ArrayList<Vehicle> updateCurrentInUseVehicle(Vehicle inUse){
    return globalVehicleList;
  }

  /**
   * this method will remove a job from the global list,
   * @param job a job we want to filter out of the list
   * @return
   */
  public ArrayList<Job> removeJobFromGlobalList(Job job){

    // since we update the global list, will call the setter and use the parameter to filter out the specific job we
    // passed in. it will return elements as long as it doesn't match the parameter
    setGlobalJobList(
            this.globalJobList.stream()
                    .filter(item -> item.getJobID() != job.getJobID()).collect(Collectors.toCollection(ArrayList::new))

    );
    return this.globalJobList;

  }

  private void setGlobalJobList(ArrayList<Job> globalJobList) {
    this.globalJobList = globalJobList;
  }
}

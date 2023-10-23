import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<User>();
    }

    public void add(JobOwner jobOwner) {
        userList.add(jobOwner);
    }
    
    public void add(VehicleOwner vehicleOwner) {
    	userList.add(vehicleOwner);
    }
}
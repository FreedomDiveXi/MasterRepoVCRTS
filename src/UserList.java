import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void add(JobOwner jobOwner) {
        if (!isUserExist(jobOwner.getUsername())) {
            userList.add(jobOwner);
        } else {
            System.out.println("User with username " + jobOwner.getUsername() + " already exists!");
        }
    }
    
    public void add(VehicleOwner vehicleOwner) {
        if (!isUserExist(vehicleOwner.getUsername())) {
            userList.add(vehicleOwner);
        } else {
            System.out.println("User with username " + vehicleOwner.getUsername() + " already exists!");
        }
    }

    public User findUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean removeUserByUsername(String username) {
        User user = findUserByUsername(username);
        if (user != null) {
            userList.remove(user);
            return true;
        }
        return false;
    }

    public void listAllUsers() {
        for (User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    private boolean isUserExist(String username) {
        return findUserByUsername(username) != null;
    }
}

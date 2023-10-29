import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    public void add(JobOwner jobOwner) {
            userList.add(jobOwner);
    }
    
    public void add(VehicleOwner vehicleOwner) {
            userList.add(vehicleOwner);
    }
    public ArrayList<User> getUsers() {
        return userList;
    }

    /*
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

     */
}

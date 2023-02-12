import com.google.gson.Gson;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager("https://api.stackexchange.com/2.3/users");
        connectionManager.addParameter("site", "stackoverflow");
        String response = connectionManager.produceRequest();
        User[] users = new Gson().fromJson(response, User[].class);
        List<User> userList = UsersManager.filterUsers(users);
        for (User u : userList) {
            System.out.println(u.toString());
        }
    }
}

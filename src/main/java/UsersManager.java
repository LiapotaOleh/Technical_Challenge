import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsersManager {
    public static List<User> filterUsers(User[] users) {
        List<User> firstFilterUsers = firstFiltration(users);

        for (User u : firstFilterUsers) {
            u.setTags();
            u.setAnswer_count();
            u.setQuestion_count();
        }

        return secondFiltration(firstFilterUsers);
    }

    private static List<User> firstFiltration(User[] beforeFilter) {
        return Arrays.stream(beforeFilter)
                .filter(user -> user.getLocation().contains("Romania") || user.getLocation().contains("Moldova"))
                .filter(user -> user.getReputation() >= 223)
                .collect(Collectors.toList());
    }

    private static List<User> secondFiltration(List<User> afterFilter) {
        return afterFilter.stream()
                .filter(user -> user.getAnswer_count() >= 1)
                .filter(user -> isHaveTags(user.getTags()))
                .collect(Collectors.toList());
    }

    private static boolean isHaveTags(String[] tags){
        boolean result = false;

        for (String s : tags) {
            if (s.equalsIgnoreCase("java")
                    || s.equalsIgnoreCase(".net")
                    || s.equalsIgnoreCase("docker")
                    || s.equalsIgnoreCase("c#")) {
                result = true;
                break;
            }
        }

        return result;
    }
}

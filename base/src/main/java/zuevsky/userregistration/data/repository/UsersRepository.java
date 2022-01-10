package zuevsky.userregistration.data.repository;

import zuevsky.userregistration.domen.User;

import java.util.Collection;
import java.util.HashMap;

public class UsersRepository {
    private static HashMap<String, User> users = new HashMap<>();

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static Collection<User> getUserCollection() {
        return users.values();
    }

    public static void setUsers(HashMap<String, User> users) {
        UsersRepository.users = users;
    }
}

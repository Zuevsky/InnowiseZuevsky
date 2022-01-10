package zuevsky.userregistration.application.validators;

import zuevsky.userregistration.domen.User;

import java.util.ArrayList;
import java.util.List;

public class UserInputValidatorTestRepositories {
    private static String[] emails = {"alexey@mail.ru", "dasha_petrova@yandex.by",
            "andrey_zeus@list.co", "anna_sadovskaya@mail.ru",
            "dmitriy.shved@gmail.com"};
    private static String[] names = {"alexey", "dasha", "andrey",
            "anna", "dmitriy"};
    private static String[] surnames = {"zuevsky", "petrova", "zeus",
            "sadovskaya", "shved"};
    private static String[][] roles = {{"SUPER_ADMIN"}, {"CUSTOMER", "ADMIN"},
            {"SUPER_ADMIN"}, {"CUSTOMER", "PROVIDER"}, {"USER", "PROVIDER"}};
    private static String[][] phones = {{"375265478952"},
            {"375465208531", "375845632015"},
            {"375874562154", "375895013247", "375863205176"},
            {"375852014766"},
            {"375648742502", "375963025418"}};

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<User>();
        for(int i = 0; i < emails.length; i++) {
            userList.add(new User((emails[i]), names[i], surnames[i],
                    new ArrayList<>(List.of(roles[i])), new ArrayList<>(List.of(phones[i]))));
        }
        return userList;
    }

    public static ArrayList<String> getPhoneList() {
        ArrayList<String> phoneList = new ArrayList();
        for(int i = 1; i < 10; i++) {
            String phone = "37533156" + i + "45" + i;
            phoneList.add(phone);
        }
        return phoneList;
    }
}

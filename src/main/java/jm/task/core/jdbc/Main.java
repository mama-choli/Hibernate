package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 18);
        userService.saveUser("Пётр", "Петров", (byte) 19);
        userService.saveUser("Максим", "Максимов", (byte) 20);
        userService.saveUser("Сидор", "Сидоров", (byte) 21);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}

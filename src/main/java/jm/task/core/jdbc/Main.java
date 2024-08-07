
package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        User user1 = userService.getAllUsers()
                .get(
                        userService.getAllUsers().size() - 1
                );
        System.out.println("User с именем - "
                + user1.getName()
                + " добавлен в базу данных");
        userService.saveUser("Name2", "LastName2", (byte) 38);
        User user2 = userService.getAllUsers()
                .get(
                        userService.getAllUsers().size() - 1
                );
        System.out.println("User с именем - "
                + user2.getName()
                + " добавлен в базу данных");
        userService.saveUser("Name3", "LastName3", (byte) 40);
        User user3 = userService.getAllUsers()
                .get(
                        userService.getAllUsers().size() - 1
                );
        System.out.println("User с именем - "
                + user3.getName()
                + " добавлен в базу данных");
        userService.saveUser("Name4", "LastName4", (byte) 12);
        User user4 = userService.getAllUsers()
                .get(
                        userService.getAllUsers().size() - 1
                );
        System.out.println("User с именем - "
                + user4.getName()
                + " добавлен в базу данных");


        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println("Размер списка Users после очистки - "
                + userService.getAllUsers().size());
        userService.dropUsersTable();

    }
}
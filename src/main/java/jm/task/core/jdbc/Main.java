package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;






public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Jeck","Sparrow", (byte) 35);
        userService.saveUser("Hector", "Barbosa", (byte) 55);
        userService.saveUser("William", "Turner ||", (byte) 27);
        userService.saveUser("Tia", "Dalma", (byte) 99);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();




    }
}

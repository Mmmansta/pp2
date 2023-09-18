package jm.task.core.jdbc;

import com.mysql.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jeck","Sparrow", (byte) 35);
        userService.saveUser("Hector", "Barbosa", (byte) 55);
        userService.saveUser("William", "Turner ||", (byte) 27);
        userService.saveUser("Tia", "Dalma", (byte) 99);
        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}

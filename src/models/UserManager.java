package models;

import java.sql.SQLException;
import java.util.Objects;

import handlers.UserDB;

public class UserManager extends Manager {
    private static String userLogin;
    private static String userPassword;
    
    @Override
    public boolean loginUser(String login, String password) throws Exception {
        userLogin = login;
        userPassword = password;
        return UserDB.loginUser(login, password);
    }
    
    @Override
    public void signUpUser(String name, String login, String password) throws SQLException {
        UserDB.signUpUser(name, login, password);
    }
    
    /**
     * The Chain of Responsibility pattern is a behavioral design pattern that allows multiple objects to handle
     * a request, with each object having the opportunity to handle the request or pass it on to the next object
     * in the chain.
     * In this method, firstly considered the condition if user typed identical login and password. If it is not true,
     * method does not allow to get operation further into database request. Saves time and space.
     */
    @Override
    public void deleteUser(String login, String password) {
        if (!Objects.equals(userLogin, login) && !Objects.equals(userPassword, password)) {
            System.out.println("Not correct login or password");
            return;
        }
        UserDB.deleteUser(login, password);
    }
}
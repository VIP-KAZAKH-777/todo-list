package models;

import java.sql.SQLException;

public interface UserInterface {
    boolean loginUser(String login, String password) throws Exception;
    void signUpUser(String a, String b, String c) throws SQLException;
    void deleteUser(String a, String b);
}
package handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB extends DBHandler{
    public static void signUpUser(String name, String login, String password) throws SQLException {
        getConnection();
        String insert = "INSERT INTO users (name, login, password) VALUES('"
                + name + "', '" + login + "', '" + PasswordHandler.encode(password) + "')";

        try {
            statement.executeUpdate(insert);
            System.out.println("User added!");
        } catch (Exception err) {
            System.out.println("User with such login already exists!");
        }
        closeConnection();
    }

    public static int getUserId(String login) throws SQLException{
        getConnection();
        int id = -1;
        String st = "SELECT * FROM users WHERE login = '" + login + "';";
        ResultSet res = statement.executeQuery(st);
        while (res.next()) id = res.getInt("id");
        closeConnection();
        return id;
    }
    
    public static boolean loginUser(String login, String password) throws Exception {
        getConnection();
        ResultSet res;
        String st = "SELECT * FROM users WHERE login = '" + login + "' AND password = '" + PasswordHandler.encode(password) + "';";
        res = statement.executeQuery(st);
        return res.next();
    }
    
    public static void deleteUser(String login, String password) {
        getConnection();
        String st = "DELETE FROM users WHERE login = '" + login + "' AND password = '" + PasswordHandler.encode(password) + "';";
        try{
            statement.executeUpdate(st);
            System.out.println("User is deleted.");
        } catch (Exception e){
            System.out.println("User cannot be deleted.");
        }finally {
            closeConnection();
        }
    }
}
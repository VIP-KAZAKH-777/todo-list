package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


abstract class DBHandler extends Config {
    protected static Connection con = null;
    protected static Statement statement = null;

    protected static void getConnection() {
        try {
            con = DriverManager.getConnection(CONNECTION_URL, ADMIN, PASSWORD);
            statement = con.createStatement();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    protected static void closeConnection() {
        try {
            con.close();
            statement.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
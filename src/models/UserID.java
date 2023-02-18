package models;

import handlers.UserDB;

import java.sql.SQLException;

/**
 * This class is the implementation of mediator pattern. It has only one field: id.
 * Local database has two tables: users and tasks. Both of them have field: user_id.
 * user_id field is used to connect tasks with their owners.
 * In this program, UserID is the connection between an objects and database methods.
 * Mediator allows you to reduce the connectivity of many classes with each other,
 * by moving these connections into one intermediary class.
 * UserID field is static, because belongs only to class.
 * Field id assigns only 1 time, when user logins into account.
 */

public class UserID {
    private static int id;

    public static void setUserId(String login) throws SQLException {
        UserID.id = UserDB.getUserId(login);
    }

    public static int getUserId() {
        return id;
    }
}
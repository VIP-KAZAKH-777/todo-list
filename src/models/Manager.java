package models;

import java.sql.SQLException;

abstract class Manager implements TaskInterface, UserInterface {
    @Override
    public boolean loginUser(String login, String password) throws Exception {
        return false;
    }

    @Override
    public void signUpUser(String a, String b, String c) throws SQLException {}

    @Override
    public void deleteUser(String a, String b) {}

    @Override
    public void showTasks() throws Exception {}

    @Override
    public void deleteTask(int a) throws Exception {}

    @Override
    public void addTask(Task task) throws SQLException {}

    @Override
    public void editTask() throws Exception {}
}
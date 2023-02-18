package models;

import java.sql.SQLException;

public interface TaskInterface {
    public void showTasks() throws Exception;
    public void deleteTask(int a) throws Exception;
    public void addTask(Task task) throws SQLException;
    public void editTask() throws Exception;
}
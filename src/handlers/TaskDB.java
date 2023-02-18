package handlers;

import models.Task;
import models.UserID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TaskDB extends DBHandler{
    public static void addTask(Task task) throws SQLException {
        getConnection();
        String insert = "INSERT INTO " + "tasks" + " (description, last_modified, user_id, status) " +
                "VALUES('" + task.getDescription() + "', '" + task.getFormattedDate(new Date(task.getDate())) + "', '"
                + UserID.getUserId() + "', " + "B'0');";

        try {
            statement.executeUpdate(insert);
            System.out.println("Task added!");
        } catch (Exception err){
            err.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public static void deleteTask(int taskId) throws SQLException{
        getConnection();
        String st = "DELETE FROM tasks WHERE id = " + taskId + ";";
        try{
            statement.executeUpdate(st);
            System.out.println("Task is deleted.");
        } catch (Exception e){
            System.out.println("Something is gone wrong.");
        } finally {
            closeConnection();
        }
    }

    public static void editTask(Task task){
        getConnection();
        String st = "UPDATE tasks SET description = '" + task.getDescription() + "', " +
                "status = " + (task.getStatus().equals("Done")? "B'1'":"B'0'") + ", " +
                "last_modified = '" + task.getFormattedDate(new Date(task.getDate())) + "' WHERE id = " + task.getId();
        try{
            statement.executeUpdate(st);
            System.out.println("Task updated.");
        } catch (SQLException e){
            System.out.println("Something is gone wrong.");
        } finally {
            closeConnection();
        }
    }
    
    public static ArrayList<Task> getTasks() throws Exception {
        getConnection();
        String st = "SELECT * FROM tasks WHERE user_id = " + UserID.getUserId();
        ResultSet res = statement.executeQuery(st);

        ArrayList<Task> tasks = new ArrayList<>();
        while (res.next()) {
            Task task = new Task(
                    res.getString("description"),
                    res.getDate("last_modified"),
                    res.getInt("status") == 1
            );
            task.setId(res.getInt("id"));
            tasks.add(task);
        }
        return tasks;
    }
}
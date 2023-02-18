package models;

import java.sql.SQLException;
import java.util.*;

import handlers.TaskDB;


public class TaskManager extends Manager {
    @Override
    public void deleteTask(int taskId) throws Exception {
        ArrayList <Task> tasks = TaskDB.getTasks();
        
        for (Task task: tasks) {
            if (task.getId() == taskId) {
                TaskDB.deleteTask(taskId);
                return;
            }
        }
        
        System.out.println("You do not have the task with such id.");
    }
    
    @Override
    public void addTask(Task task) throws SQLException {
        TaskDB.addTask(task);
    }
    /**
     * Edits task.
     * !This method might be changed in implementation by using The Chain of Responsibility pattern.
     * !UserManager class already uses this pattern.
     */
    @Override
    public void editTask() throws Exception {
        ArrayList<Task> tasks;
        tasks = TaskDB.getTasks();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Which task do you want to edit?");
        int taskId = Integer.parseInt(sc.nextLine().trim());
        
        boolean found = false;
        
        for (Task task: tasks) {
            if (task.getId() == taskId) {
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("You do not have the task with such id.");
            return;
        }
        
        Task task = new Task();
        for (Task task0: tasks)
            if (task0.getId() == taskId)
                task = task0;
        
        System.out.println("Which field you want to edit?\n" +
        "1: Description || 2: Status || 3: Both of them");
        int choice = Integer.parseInt(sc.nextLine().trim());
        
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        date.setTime(calendar.getTimeInMillis());
        task.setDate(date);
        
        switch (choice){
            case 1:
                System.out.print("Type new description: ");
                String desc = sc.nextLine();
                task.setDescription(desc);
                break;
            case 2:
                task.setStatus(!task.getStatus().equals("Done"));
                break;
            case 3:
                System.out.print("Type new description: ");
                desc = sc.nextLine();
                task.setDescription(desc);
                task.setStatus(!task.getStatus().equals("Done"));
                break;
            default:
                System.out.println("Wrong input.");
                return;
        }
        TaskDB.editTask(task);
    }
    
    @Override
    public void showTasks() throws Exception {
        ArrayList<Task> tasks;
        tasks = TaskDB.getTasks();
        for (Task task: tasks) {
            System.out.println(task);
        }
        System.out.println();
    }
}
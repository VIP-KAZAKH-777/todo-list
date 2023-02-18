import models.*;
import java.io.IOException;
import java.util.*;


public class App {
    private static final Scanner in = new Scanner(System.in);
    private static String name;
    private static String login;
    private static String password;
    private static boolean logged;
    private static boolean running = true;
    private static final UserManager userManager = new UserManager();
    private static final TaskManager taskManager = new TaskManager();

    public static void clearConsole() throws Exception, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    
    private static void authorization() throws Exception {
        System.out.println("""
                Choose the operation:
                1: Sign up || 2: Log in || 3: Exit program""");
        int choice = Integer.parseInt(in.nextLine().trim());
        switch (choice) {
            case 1 -> {
                System.out.print("Enter your name: ");
                name = in.nextLine();
                System.out.print("Enter login: ");
                login = in.nextLine();
                System.out.print("Enter password: ");
                password = in.nextLine();
                System.out.print("Repeat password: ");
                if (!in.nextLine().equals(password)) {
                    System.out.println("Passwords are not equal.");
                    return;
                }
                userManager.signUpUser(name, login, password);
            }
            case 2 -> {
                System.out.print("Enter login: ");
                login = in.nextLine();
                System.out.print("Enter password: ");
                password = in.nextLine();
                logged = userManager.loginUser(login, password);
                if (logged)
                    System.out.println("You are logged in.");
                else
                    System.out.println("Wrong login or password.");
            }
            case 3 -> running = false;
            default -> System.out.println("Wrong input.");
        }
    }

    private static void application() throws Exception {
        clearConsole();
        taskManager.showTasks();
        System.out.println("""
                Choose operation:
                1. Add task    || 2. Delete task || 3. Edit task.
                4. Delete user || 5. Exit program.""");
        int choice = Integer.parseInt(in.nextLine().trim());
        switch (choice) {
            case 1 -> {
                System.out.print("Description of the task: ");
                String description = in.nextLine();
                System.out.print("Status (true -> Done, false -> Not Done): ");
                boolean status = Boolean.parseBoolean(in.nextLine());
                Task task = new Task(description, new Date(new GregorianCalendar().getTimeInMillis()), status);
                taskManager.addTask(task);
            }
            case 2 -> {
                System.out.print("Task id: ");
                int taskID = Integer.parseInt(in.nextLine());
                taskManager.deleteTask(taskID);
            }
            case 3 -> taskManager.editTask();
            case 4 -> {
                System.out.println("Write login and password of account you are logged in.");
                System.out.print("Login: ");
                String uLogin = in.nextLine();
                System.out.println("Password: ");
                String uPass = in.nextLine();
                userManager.deleteUser(uLogin, uPass);
                logged = false;
            }
            case 5 -> running = false;
        }
    }
    public static void main(String[] args) throws Exception {
        while (!logged && running) {
            clearConsole();
            authorization();
        }
        
        if (running) UserID.setUserId(login);
        
        while (running && logged) {
            application();
        }

        clearConsole();
        System.out.println("Thank you for using Todolist application! Goodbye!");
    }
}
package ui;
import model.Controller;
import model.Project;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main{
    private Scanner reader;
	private Controller controller;

public Main() {
    reader = new Scanner(System.in);
    controller = new Controller();
}

public static void main(String[] args) {
    Main exe = new Main();
    exe.menu();
}

public void menu() {
    boolean exit = false;
    while (!exit) {
        System.out.println("\nSelect an option:");
        System.out.println("1. Register a new project");
        System.out.println("2. Search projects after a specific date");
        System.out.println("3. Search projects before a specific date");
        System.out.println("4. Exit");

        int option = reader.nextInt();
        reader.nextLine();

        switch (option) {
            case 1:
                registerProject();
                break;
            case 2:
                searchProjectsAfterDate();
                break;
            case 3:
                searchProjectsBeforeDate();
                break;
            case 4:
                exit = true;
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}

public void registerProject() {
    System.out.println("\nEnter the project name:");
    String projectName = reader.nextLine();

    System.out.println("\nEnter the client name:");
    String clientName = reader.nextLine();

    System.out.println("\nEnter the project type:");
    String projectType = reader.nextLine();

    System.out.println("\nEnter the initial date (dd/MM/yy):");
    String initialDateString = reader.nextLine();
    Calendar initialDate = parseCalendarDate(initialDateString);

    System.out.println("\nEnter the final date (dd/MM/yy):");
    String finalDateString = reader.nextLine();
    Calendar finalDate = parseCalendarDate(finalDateString);

    System.out.println("\nEnter the budget:");
    double budget = reader.nextDouble();
    reader.nextLine();

    boolean success = controller.RegisterProject(projectName, clientName, projectType, initialDate, finalDate, budget);

    if (success) {
        System.out.println("\nProject registered successfully");
    } else {
        System.out.println("\nProject registration failed. Maximum number of projects reached.");
    }
}

public void searchProjectsAfterDate() {
    System.out.println("\nEnter the date to search after (dd/MM/yy):");
    String dateString = reader.nextLine();
    String result = controller.searchProjectsAfterDate(dateString);
    System.out.println(result);
}

public void searchProjectsBeforeDate() {
    System.out.println("\nEnter the date to search before (dd/MM/yy):");
    String dateString = reader.nextLine();
    String result = controller.searchProjectsBeforeDate(dateString);
    System.out.println(result);
}

private Calendar parseCalendarDate(String dateString) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    Calendar date = Calendar.getInstance();
    try {
        date.setTime(formatter.parse(dateString));
    } catch (ParseException e) {
        System.out.println("\nInvalid date format. The date should be in the format dd/MM/yy.");
    }
    return date;
}

}
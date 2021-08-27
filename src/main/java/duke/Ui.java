package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner myScanner;
    private final String DIVIDER_LINE = "_______________________________________________";
    
    public Ui() {
        myScanner = new Scanner(System.in);
    }
    
    public void showLoadingError() {
        System.out.println("There was a problem loading saved tasks.");
    }
    
    public void showWelcome() {
        System.out.println("Hello...\nWhat do you want?\n");
    }
    
    public void showBye() {
        System.out.println("Whatever...");
    }
    
    public String readCommand() {
        return myScanner.nextLine();
    }
    
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }
    
    public void showError(String message) {
        System.out.println("Error. " + message);
    }
    
    public void showMarkDone(Task task) {
        System.out.println("I've marked this task as done:");
        System.out.println("\t" + task);
    }
    
    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n\t" + task);
        printTasksCount(tasks);
    }
    
    void printTasksCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
    }
    
    public void showAddTask(TaskList tasks, Task task) { 
        System.out.println("Got it. I've added this task:\n\t" + task);
        printTasksCount(tasks);
    }
}

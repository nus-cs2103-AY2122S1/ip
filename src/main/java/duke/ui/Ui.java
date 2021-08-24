package duke.ui;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private Parser parser;
    
    public Ui() {
        sc = new Scanner(System.in);
        parser = new Parser();
    }
    
    public void greet() {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
    }
    
    public Command receiveCommand() throws DukeException {
        System.out.println("---------------------------------");
        String cmd = sc.nextLine();
        return parser.parse(cmd);
    }
    
    public void displayTasks(List<Task> tasks) {
        System.out.println("---------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Displays the matching tasks.
     * 
     * @param tasks The matching tasks to display.
     */
    public void displayFoundTasks(List<Task> tasks) {
        System.out.println("---------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }
    
    public void displayDoneTask(Task task) {
        System.out.println("---------------------------------");
        System.out.println("I have marked this task as done");
        System.out.printf("%s%n", task);
    }
    
    public void displayRemovedTask(Task task, int count) {
        System.out.println("---------------------------------");
        System.out.println("I have removed this task");
        System.out.printf("%s%n", task);
        System.out.printf("Now you have %d tasks.%n", count);
    }
    
    public void displayAddedTask(Task task, int count) {
        System.out.println("---------------------------------");
        System.out.println("I have added this task: ");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks.%n", count);
    }
    
    public void displayErrorMessage(String msg) {
        System.out.println("---------------------------------");
        System.out.println(msg);
    }
    
    public void sayGoodbye() {
        System.out.println("---------------------------------");
        System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
        System.out.println("---------------------------------");
    }
    
    public void close() {
        sc.close();
    }
}

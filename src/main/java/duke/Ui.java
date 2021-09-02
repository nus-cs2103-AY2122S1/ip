package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Represents user interface
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs an instance of <code>Ui</code>
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");
    }

    /**
     * Scans next line
     * @return Scanned user input
     */
    public String getNextLine() {
        return this.sc.nextLine();
    }

    /**
     * Prints exception message
     * @param e Exception
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints loading error
     */
    public void showLoadingError() {
        System.out.println("Error loading save file :(");
    }

    /**
     * Prints corresponding task added message
     * @param task Task to be printed
     * @param taskList Saved tasks
     */
    public void showAddedMessage(Task task, TaskList taskList) {
        List<Task> savedTasks = taskList.getTasks();
        System.out.println("I've added this task:\n" + task);
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
    }
}

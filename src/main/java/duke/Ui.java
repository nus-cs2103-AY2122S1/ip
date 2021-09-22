package duke;

import java.util.Scanner;

/**
 * Handles the System input and output.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in); // Create a Scanner object
    }

    public void showWelcome() {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", botName);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        assert scanner != null : "scanner should not be null";
        return scanner.nextLine();
    }

    public void showLine(String s) {
        System.out.println(s);
    }

    public void showTask(Task task) {
        System.out.println(task);
    }

    public void showTaskCount(int taskCount) {
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public void showDivider() {
        System.out.println("_".repeat(50));
    }

    /**
     * Prints error message from duke.
     *
     * @param e Erorr thrown by duke.Duke.
     */
    public void showDukeException(DukeException e) {
        System.out.printf("HuAI Liddat!!! %s\n", e);
    }

    /**
     * Prints stack trace of other error messages.
     *
     * @param e Erorr thrown by Java.
     */
    public void showException(Exception e) {
        System.out.printf("HuAI Liddat!!! %s\n", e);
        // e.printStackTrace();
    }
}

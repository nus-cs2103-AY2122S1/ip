package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    Scanner sc;

    /**
     * Class constructor for Ui Class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Format print the loading error message
     */
    public void showLoadingError() {
        formatPrint("Loading Error");
    }

    /**
     * Format print the given error message
     *
     * @param errorMessage the error message that is retrieved from the error
     */
    public void showError(String errorMessage) {
        formatPrint(errorMessage);
    }

    /**
     * Format print the welcome message
     */
    public void showWelcome() {
        String greeting = "Howdy, pardner!";
        String question = "Is there anything old Vic can do you for?";
        formatPrint(greeting, question);
    }

    /**
     * Format print the farewell message
     */
    public void showFarewell() {
        String bye = "See ya round, buckaroo.";
        formatPrint(bye);
    }

    /**
     * Return the string read by scanner next line
     *
     * @return      the string read from user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Format print the given varargs into the indented format
     *
     * @param lines the varargs containing all the lines to be printed with indentation
     */
    public void formatPrint(String... lines) {
        System.out.println("\n    ____________________________________________________________");
        for (String line : lines) {
            System.out.printf("     %s\n", line);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Format print all the tasks in TaskList with indentation
     *
     * @param tasks a TaskList of all the tasks
     */
    public void printList(TaskList tasks) {
        int count = 1;
        System.out.print("\n    ____________________________________________________________" +
                "\n    Here are the tasks in your list:\n");
        for (Task task : tasks.getList()) {
            System.out.printf("     %d.%s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }
}

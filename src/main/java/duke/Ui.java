package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Class to encapsulate Ui
 */
public class Ui {
    protected Scanner sc;

    /**
     * Class constructor for Ui Class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Formats print the given error message
     *
     * @param errorMessage the error message that is retrieved from the error
     */
    public void showError(String errorMessage) {
        formatPrint(errorMessage);
    }

    /**
     * Formats print the given varargs into the indented format
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
     * Formats print all the tasks in TaskList with indentation
     *
     * @param tasks a TaskList of all the tasks
     */
    public String getList(TaskList tasks) {
        int count = 1;
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks.getList()) {
            str.append("   ").append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }
        return str.toString();
    }

    /**
     * Returns Victor's default greeting
     *
     * @return       Victor's default greeting
     */
    public static String getGreetings() {
        return "Howdy, pardner! Is there anything old Vic can do you for?";
    }
}

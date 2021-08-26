package duke.util;

import duke.task.Task;
import java.util.Scanner;

/**
 * Encapsulates user interaction.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private static final String HORIZONTAL_LINE = "----------------------------------------------------";
    private static final String WELCOME_MESSAGE= "Hello! I'm Duke " +
            "What can I do for you?\n\n" + HORIZONTAL_LINE + "-----------------------------\n" +
            "|\tPlease enter one of the following commands:                                  |\n" +
            "|\t1. todo <description> (eg. todo paint)                                       |\n" +
            "|\t2. deadline <description> /by <date> (e.g deadline submit hw /by 2020-01-01) |\n" +
            "|\t3. event <description> /at <date> (e.g event party /at 2020-01-01)           |\n" +
            "|\t4. list - see list of tasks added                                            |\n" +
            "|\t5. delete <task number> (e.g delete 1) - delete a task from list             |\n" +
            "|\t6. done <task number> (e.g done 1) - mark a task in list as done             |\n" +
            "|\t7. bye - exit duke                                                           |\n" +
            HORIZONTAL_LINE + "-----------------------------\n";

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Returns string of user command entered.
     *
     * @return String representation of user input
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints horizontal line.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns string of task added.
     *
     * @param task Task added
     * @param listLength Length of task list
     * @return String representation of task list
     */
    public String showTaskAdded(Task task, int listLength) {
        String output = String.format("added: " + task.toString()
                + "\nNow you have %s tasks in your list" , listLength);
        System.out.println(output);
        return output;
    }

    /**
     * Returns string of task deleted.
     *
     * @param task Task deleted
     * @param listLength Length of task list
     * @return String representation of task deleted
     */
    public String showTaskDeleted(Task task, int listLength) {
        String output = String.format("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , listLength);
        System.out.println(output);
        return output;
    }

    /**
     * Returns string of task done.
     *
     * @param task Task set as done
     * @return String representation of task set as done.
     */
    public String showTaskDone(Task task) {
        String output = "Nice! I've marked this task as done:\n"
                + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Prints task number in list and task details.
     *
     * @param task Task to be printed
     * @param listPosition Index of task in list
     */
    public void showTask(Task task, int listPosition) {
        System.out.printf("\t%s." + task.toString() + "%n", listPosition);
    }

    /**
     * Prints bye message.
     */
    public void showBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }


    /**
     * Returns string of error message.
     *
     * @param message Error message
     * @return String representation of error
     */
    public void showError(String message) {
        System.out.println(message);
    }

}

package dino.user;

import dino.data.TaskList;

/**
 * Represents the class which handles all the output by the program and prompts the user for input
 */
public class Ui {

    // ASCII DIVIDER to clean up the output
    public static final String DIVIDER = "----------------------------------------------------";
    public static final String DELIMITER = " | ";
    public static final String SPLIT_DELIMITER = " \\| ";

    public Ui() {
    }

    public void showLoadingError() {
        System.out.println("An error occurred.");
    }

    /**
     * Displays a message when a done command is successfully called.
     *
     * @param task String representing the task that was done
     * @return A formatted String to display to the user
     */
    public static String displayDoneTaskMessage(String task) {
        return (DIVIDER + "\n" + "Nice! I've marked this task as done: " + "\n"
                + task + "\n"
                + DIVIDER);
    }

    /**
     * Displays a message when a delete command is successfully called.
     *
     * @param task String representing the task that was deleted
     * @return A formatted String to display to the user
     */
    public static String displayDeletedTaskMessage(String task, int taskListSize) {
        return (DIVIDER + "\n" + "Nice! Noted. I've removed this task: " + "\n"
                + task + "\n"
                + "You now have " + taskListSize + " tasks remaining!" + "\n"
                + DIVIDER);
    }

    /**
     * Displays a message when a list command is successfully called.
     *
     * @param tasks TaskList representing the tasks
     * @return A formatted String of all the tasks in the taskList to display to the user
     */
    public static String displayListMessage(TaskList tasks) {
        String output = "";
        output += DIVIDER + "\n" + "Here are the items in your task list: " + "\n";
        for (int i = 0; i < tasks.getLength(); i++) {
            output += (i + 1 + ". " + tasks.getTask(i) + "\n");
        }
        output += (DIVIDER) + "\n";
        return output;
    }

    /**
     * Displays a message when a bye command is successfully called.
     *
     * @return A formatted String to display to the user
     */
    public static String displayByeMessage() {
        return DIVIDER + "\n" + "Bye. Hope to see you again soon!" + "\n" + DIVIDER;
    }

    /**
     * Displays a message which contains the tasks which match the keyword.
     *
     * @param foundTasks The tasks that match the keywords
     * @return A formatted String to display to the user
     */
    public static String findMessage(String foundTasks) {
        return DIVIDER + "\n" + "Here are the matching tasks in your list:" + "\n" + foundTasks + "\n" + DIVIDER;
    }

    /**
     * Displays a message when a todo, event or deadline command is successfully called.
     *
     * @param tasks String representing the task that was added
     * @return A formatted String to display to the user
     */
    public static String displayAddTaskMessage(TaskList tasks) {
        return DIVIDER + "\n" + "added: " + tasks.getTask(tasks.getLength() - 1) + "\n"
                + "now you have: " + tasks.getLength() + " tasks! type 'list' to see them!" + "\n" + DIVIDER;
    }

    public static String displayDinoExceptionMessage(DinoException e) {
        return DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER;
    }

    public static String displayExceptionMessage(Exception e) {
        return DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER;
    }


}

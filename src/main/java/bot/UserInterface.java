package bot;

import java.util.Scanner;

import task.Task;

/**
 * A class that encapsulates all UI components of Duke.
 */
public class UserInterface {

    private static final String divider = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";

    private final Scanner reader;

    /**
     * Constructor for the UserInterface class.
     */
    UserInterface() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Returns the formatted error message to be displayed to the user.
     *
     * @param error The error message to be shown to the user.
     * @return A String representation of the error.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns the formatted error message to be displayed to the user, specifically when local data is not found.
     *
     * @return A String representation of the error.
     */
    public String showStorageLoadingError() {
        return "Creating a new storage for you...";
    }

    /**
     * Returns the welcome message to be displayed to the user.
     *
     * @return A String representation of the Welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! My name is DUKE!\n\n"
                + "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n"
                + "░█░░░░░░░░▀▄░░░░░░▄░\n"
                + "█░░▀░░▀░░░░░▀▄▄░░█░█\n"
                + "█░▄░█▀░▄░░░░░░░▀▀░░█\n"
                + "█░░▀▀▀▀░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n"
                + "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n"
                + "░░▀░░░▀░░░░░▀░░░▀░░░\n\n"
                + "What can I do for you today?\n\n"
                + "(Please refer to our user guide at https://lhw-1.github.io/ip/ for more information on how to use the bot.)";
    }

    /**
     * Returns the exit message to be displayed to the user.
     *
     * @return A String representation of the Exit message.
     */
    public String showExitMessage() {
        return "Bye... Hope to see you again soon!";
    }

    /**
     * Returns the message to be displayed to the user when a task is marked as done.
     *
     * @param task The task marked as done, and to be displayed to the user.
     * @param index The index of the task inside the task list.
     * @return A String representation of the message.
     */
    public String showTaskDone(String task, int index) {
        return "Great! I've marked the following task as done:\n" + index + ". " + task;
    }

    /**
     * Returns the message to be displayed to the user when a list of tasks that matches the user's query is found.
     *
     * @param foundTasks A String representation of the list of tasks found that match the user's query.
     * @return A String representation of the message.
     */
    public String showTaskFound(String foundTasks) {
        if (foundTasks.equals("")) {
            return "It looks like there are no tasks matching that...";
        } else {
            return "Here are the matching tasks in your list:\n\n"
                    + foundTasks
                    + "\nHope you found what you were looking for!";
        }
    }

    /**
     * Returns the message to be displayed to the user when a task is deleted.
     *
     * @param deletedTask The task deleted, and to be displayed to the user.
     * @return A String representation of the message.
     */
    public String showTaskDeleted(String deletedTask) {
        return "Noted. I've removed the following task:\n\n" + deletedTask;
    }

    /**
     * Returns the message to be displayed to the user when a task is deleted.
     *
     * @param tasks A String representation of the list of tasks stored by Duke.
     * @return A String representation of the message.
     */
    public String showTaskList(String tasks) {
        return tasks;
    }

    /**
     * Shows the message for a task being added, according to the type of task given.
     *
     * @param task  The task to be added to the list.
     * @param length The current length of the list.
     * @return A String representation of the message.
     */
    public String showTaskAdded(Task task, int length) {
        return "Alright. I've added the following "
                + task.getTaskType()
                + ":\n--> "
                + task.getTaskState()
                + "\n\nYou now have "
                + (length + 1)
                + (length == 0 ? " task" : " tasks")
                + " in the list.\n";
    }

    /**
     * Shows the message for a task being tagged, according to the type of task given.
     *
     * @param task  The task to be tagged.
     * @return A String representation of the message.
     */
    public String showTaskTagged(Task task) {
        return "Alright. I've tagged the following "
                + task.getTaskType()
                + ":\n--> "
                + task.getTaskState();
    }
}

package duke;

import java.util.ArrayList;

/**
 * This class encapsulated the management of the user interface.
 */
public class Ui {

    /**
     * Introduction method shown when program is first run.
     */
    public String introductionMessage() {
        String message = "Hello I'm\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?";
        return message;
    }

    /**
     * Goodbye message when the user enters the "bye" command.
     */
    public String getByeMessage() {
        String message = "Duke says: Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Prints out the list of tasks.
     *
     * @param tasks The current list of tasks.
     */
    public String getListTasksMessage(ArrayList<Task> tasks) {
        String message = "Duke says: Here is your list of tasks :)\n";

        if (tasks.size() == 0) {
            message += "Looks like you don't have any pending tasks!\nMust be nice (-_-;)";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String newTask = i + 1 + "."
                        + tasks.get(i).toString() + "\n";
                message += newTask;
            }
        }
        return message;
    }

    /**
     * Prints confirmation that a task was added successfully.
     *
     * @param task The task that was added.
     * @param numTasks The new total number of tasks.
     */
    public String getTaskAdditionMessage(Task task, int numTasks) {
        String message = "Duke says: I've added the task: \n"
                + "     " + task.toString() + "\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
        return message;
    }

    /**
     * Prints confirmation that a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @param numTasks The new total number of tasks.
     */
    public String getTaskDeletionMessage(Task task, int numTasks) {
        String message = "Duke says: I've deleted the task: \n"
                + "     " + task.toString() + "\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
        return message;
    }

    /**
     * Prints confirmation that a task was successfully completed.
     *
     * @param task The task that was completed.
     * @param numTasks The current total number of tasks.
     */
    public String getTaskCompletionMessage(Task task, int numTasks) {
        String message = "Duke says: You've completed the task: \n"
                + "     " + task.toString() + "Well done!\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
        return message;
    }

    /**
     * Prints an error message to the user.
     *
     * @param msg The error that was thrown.
     */
    public String getErrorMessage(Exception msg) {
        String message = msg.getMessage();
        return message;
    }

    /**
     * Prints a message to tell the user that Duke does not understand their input.
     */
    public String getUnknownCommandMessage() {
        String message = "Duke says: Sorry I don't understand what that means";
        return message;
    }

    /**
     * Prints a message to tell the user that the task number they are trying to complete/delete does not exist.
     */
    public String getInvalidIndexMessage() {
        String message = "Duke says: You don't have that many tasks!";
        return message;
    }
}

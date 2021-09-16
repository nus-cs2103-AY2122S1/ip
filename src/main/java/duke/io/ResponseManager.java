package duke.io;

import duke.task.Task;

import java.util.ArrayList;

/**
 * This class encapsulated the management of the user interface.
 */
public class ResponseManager {
    /**
     * Goodbye message when the user enters the "bye" command.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the list of tasks.
     *
     * @param tasks The current list of tasks.
     */
    public String getListTasksMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder(
                "Here is your list of tasks\n");

        if (tasks.size() == 0) {
            message.append(
                    "Looks like you don't have any pending tasks!\nMust be nice (-_-;)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String newTask = i + 1 + "."
                        + tasks.get(i).toString() + "\n";
                message.append(newTask);
            }
        }
        return message.toString();
    }

    /**
     * Prints confirmation that a task was added successfully.
     *
     * @param task The task that was added.
     * @param numTasks The new total number of tasks.
     */
    public String getTaskAdditionMessage(Task task, int numTasks) {
        return "I've added the task: \n"
                + "     " + task.toString() + "\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
    }

    /**
     * Prints confirmation that a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @param numTasks The new total number of tasks.
     */
    public String getTaskDeletionMessage(Task task, int numTasks) {
        return "I've deleted the task: \n"
                + "     " + task.toString() + "\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
    }

    /**
     * Prints confirmation that a task was successfully completed.
     *
     * @param task The task that was completed.
     * @param numTasks The current total number of tasks.
     */
    public String getTaskCompletionMessage(Task task, int numTasks) {
        return "You've completed the task: \n"
                + "     " + task.toString() + "Well done!\n"
                + "You now have " + numTasks + " tasks, jiayouz!";
    }

    public String getSnoozeTaskMessage(Task task) {
        return "The task: \n"
                + "     " + task.toString() + "\n"
                + "has been snoozed\n";
    }

    /**
     * Prints an error message to the user.
     *
     * @param msg The error that was thrown.
     */
    public String getErrorMessage(Exception msg) {
        return msg.getMessage();
    }

    /**
     * Prints a message to tell the user that Duke does not understand their input.
     */
    public String getUnknownCommandMessage() {
        return "\u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \n" +
                "Sorry I don't understand what that means";
    }

    /**
     * Prints a message to tell the user that the task number they are trying to complete/delete does not exist.
     */
    public String getInvalidIndexMessage() {
        return "\u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \n" +
                "You don't have that many tasks!";
    }
}

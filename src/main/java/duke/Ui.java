package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns a greeting message
     * @return A greeting message
     */
    public static String greet() {
        return "Hello! I'm Duke.\n" +
                "What can I do for you?";
    }

    /**
     * Returns a parting message
     * @return A parting message
     */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message to the user that the task has been added to the list
     * @param task The task that has been added to the list
     * @param size Size of the current list of tasks
     * @return A message to the user that the task has been added to the list
     */
    public static String addTask(Task task, int size) {
        String reply = "Got it. I've added this task:\n  " +
                task + "\n" +
                "Now you have " + size + " tasks" + " in the list.";
        return reply;
    }

    /**
     * Returns a message to the user that the task has been marked as done
     * @param task The task that has been marked as done
     * @return A message to the user that the task has been marked as done
     */
    public static String doneTask(Task task) {
        String reply = "Nice! I've marked this task as done:\n" +
                task;
        return reply;
    }

    /**
     * Returns a message to the user that the task has been deleted
     * @param task The task that has been deleted
     * @param size Size of the current list of tasks
     * @return
     */
    public static String deleteTask(Task task, int size) {
        String reply = "Noted. I've removed this task:\n" +
                task + "\n"
                + "Now you have " + size + " tasks" + " in the list.\n";
        return reply;
    }

    /**
     * Returns a message to the user containing the tasks that match the given search string
     * @param matches The tasks that match the given search string
     * @return A message to the user containing the tasks that match the given search string
     */
    public static String findTasks(ArrayList<Task> matches) {
        String reply = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task task : matches) {
            reply += "  " + count + ". " + task + "\n";
            count++;
        }
        return reply;
    }

    /**
     * Returns a message to the user with the updated task
     * @param task The updated task
     * @return A message to the user with the updated task
     */
    public static String editTask(Task task) {
        String reply = "Noted. I've updated the task:\n" +
                task;
        return reply;
    }
}

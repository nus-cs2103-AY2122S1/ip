package duke;
import java.util.ArrayList;

/**
 * Represents the DukeUI to interact with the user.
 */
public class DukeUI {

    public void goodBye() {
        System.out.println("Goodbye!");
    }

    /**
     * Greets user on startup.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Informs the user that the task they requested
     * to mark as done is done.
     * @param task The task that was marked as done.
     */
    public void markTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Informs the user that the task they requested
     * to mark as deleted is deleted.
     * @param tasksLength The number of tasks left after deletion.
     */
    public void showDeleteTaskMessage(int tasksLength) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("Now you have " + tasksLength + " tasks in the list.");
    }

    /**
     * Informs the user that the task they requested
     * to add was added.
     * @param tasksLength The number of tasks after adding.
     * @param task The task that was added.
     */
    public void showTaskAddedMessage(int tasksLength, String task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasksLength + " tasks in the list.");
    }

    /**
     * Return the list of tasks.
     * @param tasks The tasks.
     */
    public void showTasksWithKeyword(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }
}

package duke;
import java.util.ArrayList;

/**
 * Represents the DukeUI to interact with the user.
 */
public class DukeUi {

    public void respondToUser(String response) {
        System.out.println(response);
    }

    public String goodBye() {
        return "Goodbye!";
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
        String firstLine = "Hello from \n" + logo + "\n";
        String secondLine = "What can I do for you?";
        System.out.println(firstLine + secondLine);
    }

    /**
     * Informs the user that the task they requested
     * to mark as done is done.
     *
     * @param task The task that was marked as done.
     */
    public String markTaskDone(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Shows schedule on a certain date.
     * @param tasks The lists of tasks on that date.
     * @param date The date to find the tasks.
     * @return String containing a line-separated list of all the tasks.
     */
    public String showSchedule(ArrayList<Task> tasks, String date) {
        if (tasks.size() == 0) {
            return "You don't have any tasks on that day! Yay!";
        }
        String response = "Here are the tasks you have on " + date + ": \n";
        for (int i = 0; i < tasks.size(); i++) {
            response += tasks.get(i).toString() + "\n";
        }
        return response;
    }

    /**
     * Informs the user that the task they requested
     * to mark as deleted is deleted.
     *
     * @param tasksLength The number of tasks left after deletion.
     */
    public String showDeleteTaskMessage(int tasksLength) {
        return "Noted. I've removed this task: \n"
                + "Now you have " + tasksLength + " tasks in the list.";
    }

    /**
     * Informs the user that the task they requested
     * to add was added.
     *
     * @param tasksLength The number of tasks after adding.
     * @param task The task that was added.
     */
    public String showTaskAddedMessage(int tasksLength, String task) {
        return "Got it. I've added this task: \n"
                + task + "\n Now you have "
                + tasksLength + " tasks in the list.";
    }

    /**
     * Return the list of tasks.
     *
     * @param tasks The tasks.
     */
    public String showTasksWithKeyword(ArrayList<Task> tasks) {
        String response = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            response += tasks.get(i).toString() + "\n";
        }
        return response;
    }
}

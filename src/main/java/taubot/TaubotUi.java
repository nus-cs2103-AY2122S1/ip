package taubot;
import java.util.ArrayList;

/**
 * Represents the TaubotUI to interact with the user.
 */
public class TaubotUi {

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
        String logo = "████████╗ █████╗ ██╗   ██╗██████╗  ██████╗ ████████╗\n"
                + "╚══██╔══╝██╔══██╗██║   ██║██╔══██╗██╔═══██╗╚══██╔══╝\n"
                + "   ██║   ███████║██║   ██║██████╔╝██║   ██║   ██║   \n"
                + "   ██║   ██╔══██║██║   ██║██╔══██╗██║   ██║   ██║   \n"
                + "   ██║   ██║  ██║╚██████╔╝██████╔╝╚██████╔╝   ██║   \n"
                + "   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝  ╚═════╝    ╚═╝   \n"
                + "                                                    \n"
                + "\n";
        String firstLine = "hey, i'm \n" + logo + "\n";
        String secondLine = "what do you want?";
        System.out.println(firstLine + secondLine);
    }

    /**
     * Informs the user that the task they requested
     * to mark as done is done.
     *
     * @param task The task that was marked as done.
     */
    public String markTaskDone(Task task) {
        return "ok this is done: \n" + task;
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
        String response = "this is what you have on " + date + ": \n";
        if (tasks.size() == 0) {
            return "you got nothing on that day :-)";
        } else if (date.equals("")) {
            response = "these are the deadlines and events: \n";
        }
        for (Task task : tasks) {
            response += task.toString() + "\n";
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
        return "ok, i've removed this task: \n"
                + "now you have " + tasksLength + " tasks in the list.";
    }

    /**
     * Informs the user that the task they requested
     * to add was added.
     *
     * @param tasksLength The number of tasks after adding.
     * @param task The task that was added.
     */
    public String showTaskAddedMessage(int tasksLength, String task) {
        return "ok, i've added this task: \n"
                + task + "\n now you have "
                + tasksLength + " tasks in the list.";
    }

    /**
     * Return the list of tasks.
     *
     * @param tasks The tasks.
     */
    public String showTasksWithKeyword(ArrayList<Task> tasks) {
        String response = "these are the tasks you were looking for: \n";
        if (tasks.size() == 0) {
            response = "there are no tasks matching that keyword";
        }
        for (Task task : tasks) {
            response += task.toString() + "\n";
        }
        return response;
    }
}

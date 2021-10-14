package duke.ui;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Greets user when programme is run
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n" + "Enter help for list of commands!";
    }

    /**
     * Bids user goodbye when programme is exited
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows error message for issue with loading of data
     */
    public String showLoadingError() {
        return "Sorry! There was an error while trying to load past records :(";
    }

    public String showAfterHelp() {
        return "Hope I was of help!";
    }

    /**
     * Shows user task has been added to list
     *
     * @param task task to be added
     * @param size current number of tasks
     */
    public String showTaskAdded(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        return String.format("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n", task, size, t);
    }

    /**
     * Shows user task has been completed
     *
     * @param task completed task
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Shows user task has been deleted
     *
     * @param task task to be deleted
     * @param size current number of tasks
     */
    public String showTaskDeleted(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        return String.format("Noted.I've removed this task:\n%s\nNow you have %d %s in the list\n", task, size, t);
    }

    /**
     * Lists required tasks in the list
     *
     * @param list list of tasks
     * @param cmd  determine which message to be shown
     */
    public String showTaskList(TaskList list, String cmd) {
        if (!list.getList().isEmpty()) {
            StringBuilder sb = new StringBuilder();

            String msg = getShowListMessage(cmd);
            sb.append(msg).append("\n");

            generateTasks(list, sb);

            return sb.toString();
        }
        return getEmptyListMsg(cmd);
    }

    private String getShowListMessage(String cmd) {
        assert (cmd.equals("list") || cmd.equals("find") || cmd.equals("past"))
            : "Command is invalid";
        switch (cmd) {
        case "list":
            return "Here are the tasks in your list:";
        case "find":
            return "Here are the matching tasks in your list:";
        case "past":
            return "Here are your past records!";
        default:
            return "There was an error showing the list :(";
        }
    }

    private String getEmptyListMsg(String cmd) {
        switch (cmd) {
        case "list":
            return "You have no tasks!";
        case "find":
            return "You have no matching tasks!";
        case "past":
            return "You have no past records!";
        default:
            return "There was an error showing the list :(";
        }
    }

    private void generateTasks(TaskList list, StringBuilder sb) {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            String s = String.format("%d.%s%n", i + 1, t);
            sb.append(s);
        }
    }
}

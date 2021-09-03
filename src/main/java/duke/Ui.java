package duke;

import duke.task.Task;

import java.io.File;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Greets user when programme is run
     */
    public String welcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Bids user goodbye when programme is exited
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows error message for issue with loading of data
     */
    public String showLoadingError() {
        return "Sorry! There was an error while trying to load past records :(";
    }

    /**
     * Shows user task has been added to list
     *
     * @param task task to be added
     * @param size current number of tasks
     */
    public String showTaskAdded(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        String s = String.format("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n", task, size, t);
        return s;
    }

    /**
     * Shows user task has been completed
     *
     * @param task completed task
     */
    public String showTaskDone(Task task) {
        String s = String.format("Nice! I've marked this task as done:\n" + task);
        return s;
    }

    /**
     * Shows user task has been deleted
     *
     * @param task task to be deleted
     * @param size current number of tasks
     */
    public String showTaskDeleted(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        String s = String.format("Noted.I've removed this task:\n%s\nNow you have %d %s in the list\n", task, size, t);
        return s;
    }


    /**
     * Lists required tasks in the list
     *
     * @param list  list of tasks
     * @param cmd determine which message to be shown
     */
    public String showTaskList(TaskList list, String cmd) {
        if (!list.getList().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            String msg = cmd.equals("list")
                    ? "Here are the tasks in your list:"
                    : cmd.equals("find")
                    ? "Here are the matching tasks in your list:"
                    : "Here are your past records!";
            sb.append(msg).append("\n");
            for (int i = 0; i < list.getSize(); i++) {
                Task t = list.getTask(i);
                String s = String.format("%d.%s%n", i + 1, t);
                sb.append(s);
            }
            return sb.toString();
        } else {
            String msg = cmd.equals("past")
                    ? "You have no past records!"
                    : "You have no tasks!";
            return msg;
        }
    }
}

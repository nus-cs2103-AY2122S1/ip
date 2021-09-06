package duke;

/**
 * A class that handles the responses given back to the user.
 */
public class Ui {

    /**
     * Gets exit message.
     *
     * @return the exit message
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets loading error message.
     *
     * @return the loading error message
     */
    public String getLoadingErrorMessage() {
        return "There is a problem loading saved data.";
    }

    /**
     * Gets add task message.
     *
     * @return the add message
     */
    public String getAddTaskMessage(Task task, int numOfTasks) {
        assert numOfTasks >= 1;
        return "Got it. I've added this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    /**
     * Gets delete task message.
     *
     * @return the delete message
     */
    public String getDeleteTaskMessage(Task task, int numOfTasks) {
        assert numOfTasks >= 0;
        return "Noted. I've removed this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    /**
     * Gets list of tasks
     *
     * @return the list of tasks
     */
    public String getTaskList(TaskList tasks) {
        return "Here are the tasks in your list:" + tasks.toString();
    }

    /**
     * Gets tasks that match the input
     *
     * @return the matched tasks
     */
    public String getFoundTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:" + tasks.toString();
    }

    /**
     * Gets finished tasks
     *
     * @return the finished tasks
     */
    public String getTasksDone(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task.toString();
    }

    /**
     * Gets error message
     *
     * @return the error message
     */
    public String getErrorMessage(String s) {
        return s;
    }
}

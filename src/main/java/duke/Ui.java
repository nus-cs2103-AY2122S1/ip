package duke;

public class Ui {
    /**
     * Show welcome message
     *
     * @return welcome message
     */
    public String showWelcome() {
        String message = "Hello! I'm Duke\n" + "What can I do for you?";
        return message;
    }

    /**
     * Show error loading task file
     *
     * @return error message
     */
    public String showLoadingError() {
        String message = "No task list file found! Creating a new file for you (:";
        return message;
    }

    /**
     * Show task added message
     *
     * @param task
     * @param size
     * @return task added message
     */
    public String showAddedTask(Task task, int size) {
        String message = "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Show task deleted message
     *
     * @param task
     * @param size
     * @return task deleted message
     */
    public String showDeletedTask(Task task, int size) {
        String message = "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
        return message;
    }

    /**
     * Show completed task message
     *
     * @param task
     * @return completed task message
     */
    public String showCompletedTask(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        return message;
    }

    /**
     * Show list of tasks
     *
     * @param tasks
     * @return list of tasks
     */
    public String showTaskList(TaskList tasks) {
        String message = tasks.toString();
        return message;
    }

    /**
     * Show goodbye message
     *
     * @return goodbye message
     */
    public String showBye() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Show error message
     *
     * @param message
     * @return error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Show searched tasks
     *
     * @param taskList
     * @return searched tasks
     */
    public String showFindTask(String taskList) {
        String message = "Here are the matching tasks in your list:\n" + taskList;
        return message;
    }
}

package duke;

/**
 * Represents the user interface that will be seen by the user when using Duke.
 */
public class Ui {

    /**
     * Class constructor that constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Displays welcome message.
     */
    public String welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n"
                + logo + "\n"
                + "What can I do for you?";
    }

    /**
     * Displays error message.
     *
     * @param error DukeException that was caught.
     */
    public String showError(DukeException error) {
        return error.toString();
    }

    /**
     * Displays bye message.
     */
    public String bye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays the tasks in the TaskList.
     *
     * @param taskList TaskList to be displayed.
     */
    public String listAll(TaskList taskList) {
        return "Here are the tasks in your list:\n"
                + taskList;
    }

    /**
     * Displays done message with the associated task.
     *
     * @param task The task that was done.
     */
    public String doneTask(Task task) {
        return "Nice! I have marked this task as done!\n"
                + task;
    }

    /**
     * Displays message that a task is added and also informs how many tasks are in the list after.
     *
     * @param task The task that is added.
     * @param taskList TaskList that the task is added to.
     */
    public String addTask(Task task, TaskList taskList) {
        int length = taskList.noOfTask();

        return "Added task:\n"
                + task + "\n"
                + "You have " + length + " tasks in the list";
    }

    /**
     * Displays message that a task is deleted and also informs how many tasks are in the list after.
     *
     * @param task The task that is deleted.
     * @param taskList TaskList that the task is deleted from.
     */
    public String deleteTask(Task task, TaskList taskList) {
        int length = taskList.noOfTask();

        return "Ok! I have removed this task:\n"
                + task + "\n"
                + "Now you have " + length + " tasks in the list.";
    }

    public String findTask(TaskList taskList) {
        int length = taskList.noOfTask();

        if (length != 0) {
            return "Here are the matching tasks in your list:\n"
                    + taskList;
        } else {
            return "Oh no there are no matching tasks in your list :((";
        }

    }
}

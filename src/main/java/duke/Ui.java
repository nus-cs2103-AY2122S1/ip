package duke;

/**
 * Represents the user interface that will be seen by the user when using Duke.
 */
public class Ui {

    /**
     * Class constructor that constructs an Ui object.
     */
    public Ui() {
    }

    /**
     * Displays error message.
     *
     * @param error DukeException that was caught.
     */
    public String showError(DukeException error) {
        return error.toString();
    }

    private String showReminder(TaskList taskList) {
        if (taskList.getNoOfReminders() == 0) {
            return "\n\nYou did not set any reminders for yourself thus far!!";
        } else {
            return "\n\nAlso, please be reminded to do these tasks by the deadline!!\n"
                    + taskList.reminderToString()
                    + "\n";
        }
    }

    /**
     * Displays the tasks in the TaskList.
     *
     * @param taskList TaskList to be displayed.
     */
    public String listAll(TaskList taskList) {
        return "Here are the tasks in your list:\n"
                + taskList
                + showReminder(taskList);
    }

    /**
     * Displays done message with the associated task.
     *
     * @param taskList The taskList that is being processed, task will be obtained from the most recent.
     */
    public String doneTask(TaskList taskList) {
        return "Nice! I have marked this task as done!\n"
                + taskList.getMostRecent()
                + showReminder(taskList);
    }

    /**
     * Displays message that a task is added and also informs how many tasks are in the list after.
     *
     * @param taskList TaskList that the task is added to.
     */
    public String addTask(TaskList taskList) {
        int length = taskList.getNoOfTasks();

        return "Added task:\n"
                + taskList.getMostRecent() + "\n"
                + "You have " + length + " tasks in the list"
                + showReminder(taskList);
    }

    /**
     * Displays message that a task is deleted and also informs how many tasks are in the list after.
     *
     * @param taskList TaskList that the task is deleted from.
     */
    public String deleteTask(TaskList taskList) {
        int length = taskList.getNoOfTasks();

        return "Ok! I have removed this task:\n"
                + taskList.getMostRecent() + "\n"
                + "Now you have " + length + " tasks in the list."
                + showReminder(taskList);
    }

    public String findTask(TaskList taskList) {
        int length = taskList.getNoOfTasks();

        if (length != 0) {
            return "Here are the matching tasks in your list:\n"
                    + taskList;
        } else {
            return "Oh no there are no matching tasks in your list :((";
        }

    }
}

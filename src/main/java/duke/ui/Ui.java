package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface which includes methods that returns information to the user.
 */
public class Ui {

    public Ui() {}

    /**
     * Returns the welcome message of the programme.
     */
    public static String showWelcomeMessage() {
        return Message.MESSAGE_WELCOME;
    }

    /**
     * Returns the exit message of the programme.
     */
    public static String showExitMessage() {
        return Message.MESSAGE_EXIT;
    }

    /**
     * Returns the error occurred in Duke.
     *
     * @param error the error occurred in Duke
     */
    public String showErrorMessage(String error) {
        return error;
    }

    /**
     * Returns a message when a Task is removed.
     *
     * @param removedTask the task removed
     * @param tasks the TaskList
     */
    public String showDeleteTaskMessage(Task removedTask, TaskList tasks) {
        String displayedMessage = "Noted. I've removed this task:\n"
                + "  " + removedTask.toString() + "\n"
                + showTotalTaskNumber(tasks);
        return displayedMessage;
    }

    /**
     * Returns a message when a Task is successfully added.
     *
     * @param task the task added
     * @param tasks the current task list
     */
    public String showAddTaskMessage(Task task, TaskList tasks) {
        String successMessage = "Got it. I've added this task:";
        String taskString = task.toString();
        String result = successMessage + "\n"
                + "  " + taskString + "\n"
                + showTotalTaskNumber(tasks);
        return result;
    }

    /**
     * Returns a message when a Task is marked as complete.
     *
     * @param task the marked task
     */
    public String showMarkTaskMessage(Task task) {
        String displayedMessage = "Nice! I've marked this task as done:\n"
                + "  " + task.toString();
        return displayedMessage;
    }

    /**
     * Returns a message when matching tasks are found or not.
     *
     * @param tasks the current task list
     */
    public String showMatchingTasksMessage(TaskList tasks) {
        if (tasks.isEmpty()) {
            String noMatchingTaskMessage = "  " + "No task matched the search term!";
            return noMatchingTaskMessage;
        } else {
            String matchingTasksPresentMessage = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= tasks.size(); i++) {
                Task currentMatchingTask = tasks.getTask(i - 1);
                String matchingTaskDisplayed = i + ". " + currentMatchingTask.toString() + "\n";
                matchingTasksPresentMessage += matchingTaskDisplayed;
            }
            return matchingTasksPresentMessage;
        }
    }

    /**
     * Returns a message that shows all tasks present in the task list.
     *
     * @param tasks the current task list
     */
    public String showAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            String noTaskMessage = "There are currently no tasks yet!";
            return noTaskMessage;
        } else {
            boolean isPlural = tasks.size() > 1;
            String message;
            if (isPlural) {
                message = "Here are the tasks in your list:\n";
            } else {
                message = "Here is the task in your list:\n";
            }
            for (int i = 1; i <= tasks.size(); i++) {
                Task currentTask = tasks.getTask(i - 1);
                String displayedTask = i + ". " + currentTask.toString() + "\n";
                message += displayedTask;
            }
            return message;
        }
    }

    /**
     * Returns a string message that shows the number of tasks currently.
     *
     * @param tasks the current task list
     * @return a string message with the total task number
     */
    private String showTotalTaskNumber(TaskList tasks) {
        boolean isPlural = tasks.size() > 1;
        String sCharacter = isPlural ? "s" : "";
        return String.format("Now you have %d task%s in the list.", tasks.size(), sCharacter);
    }

    /**
     * Returns a string message that shows the updated task.
     *
     * @param updatedTask the updated task
     * @return a string message with the updated task
     */
    public String showEditTaskMessage(Task updatedTask) {
        String successMessage = "Got it. I've updated this task:";
        String taskString = updatedTask.toString();
        String result = successMessage + "\n"
                + "  " + taskString;
        return result;
    }
}

package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Responsible for returning messages for the user.
 */
public class Ui {
    private final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns an exit message.
     *
     * @return Exit message
     */
    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Returns a message listing all the user's tasks.
     *
     * @param tasks User's task list.
     * @return Returns a message listing all the user's tasks.
     */
    public String showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n" + tasks);
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Returns a message confirming that the task was added.
     *
     * @param task Task added.
     * @param numRemainingTasks Total number of tasks user has.
     * @return Message confirming that the task was added.
     */
    public String showTaskAddedMessage(Task task, int numRemainingTasks) {
        return "Got it. I've added this task:\n  " + task + '\n'
            + "You have " + numRemainingTasks + " tasks in the list\n";
    }

    /**
     * Returns a message confirming that the task was deleted.
     *
     * @param task Task to be deleted.
     * @param numRemainingTasks Total number of tasks user has left.
     * @return Message confirming that the task was deleted.
     *
     */
    public String showTaskDeletedMessage(Task task, int numRemainingTasks) {
        return "Noted. I've removed this task:\n " + task + '\n'
            + "You have " + numRemainingTasks + " tasks in the list\n";
    }

    /**
     * Returns message confirming that the task was marked done.
     *
     * @param task Task marked done.
     * @return Message confirming that the task was marked done.
     */
    public String showTaskDoneMessage(Task task) {
        return "Nice! this task has been marked done:\n  " + task + "\n";
    }

    /**
     * Returns list of filtered tasks.
     *
     * @param tasks List of filtered tasks
     * @return List of filtered tasks.
     */
    public String showFilteredTaskList(TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }

    /**
     * Returns an error message.
     *
     * @param e Exception object.
     * @return Exception object's error message.
     */
    public String showErrorMessage(Exception e) {
        return e.getMessage();
    }
}

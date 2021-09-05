package duke.ui;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static String showWelcomeMessage() {
        return "Hello from Duke! \n"
                + "What do you need to do today?";
    }

    /**
     * Generates and prints the Goodbye message before exiting Duke.
     */
    public static String showExitMessage() {
        return "See you! Have a nice day!";
    }

    /**
     * Shows the list of tasks to user, formatted as an indexed list.
     *
     * @param list the tasklist to show
     */
    public static String showTaskList(TaskList list) {
        return list.toDisplay();


    }

    /**
     * Shows the last task added to the tasklist upon successful addition of a task.
     *
     * @param list the tasklist that is added unto
     */
    public static String showAddedTask(TaskList list) {
        return String.format("added: %s\n", list.getTask(list.count()));
    }

    /**
     * Shows the task deleted to the tasklist upon successful deletion of a task.
     *
     * @param task the deleted task
     */
    public static String showDeletedTask(Task task) {
        return "Got it! I've removed this task: \n" + task + "\n";
    }

    /**
     * Shows the task completed to the tasklist upon successful marking of a task as Done.
     *
     * @param task the completed task
     */
    public static String showDoneTask(Task task) {
        return "Good job! I've marked this task as done: \n" + task;
    }

    /**
     * Shows the number of tasks in the tasklist.
     *
     * @param tasks the tasklist
     */
    public static String showTaskCount(TaskList tasks) {
        return String.format("Now you have %d tasks in your list.%n%n", tasks.count());

    }

    /**
     * Shows the description of error when an operation fails.
     *
     * @param e the exception that caused the error
     */
    public static String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Shows the search results by displaying the matching tasks in
     * an indexed list.
     *
     * @param results ArrayList of matching tasks
     */
    public static String showFindResults(ArrayList<Task> results) {
        if (results.isEmpty()) {
            return "Sorry, no matches found :(";
        } else {
            StringBuilder searchResults = new StringBuilder("Here are the matching tasks in your list: \n");
            int index = 1;
            for (Task item : results) {
                searchResults.append(index).append(". ").append(item).append("\n");
                index += 1;
            }
            return searchResults.toString();
        }
    }
}

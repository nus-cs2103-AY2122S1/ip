package duke.ui;

import java.util.ArrayList;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;

/**
 * Text UI of the application.
 */
public class Ui {

    /**
     * Generates and prints the welcome message upon the start of the application.
     *
     * @return Duke's default welcome message
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
     * @return indexed list of tasks
     */
    public static String showTaskList(TaskList list) {
        return list.toDisplay();


    }

    /**
     * Shows the last task added to the tasklist upon successful addition of a task.
     *
     * @param list the tasklist that is added unto
     * @return description of the newly added task
     */
    public static String showAddedTask(TaskList list) {
        return String.format("added: %s\n", list.getTask(list.count()));
    }

    /**
     * Shows the task deleted to the tasklist upon successful deletion of a task.
     *
     * @param task the deleted task
     * @return description of task deleted
     */
    public static String showDeletedTask(Task task) {
        return "Got it! I've removed this task: \n" + task + "\n";
    }

    /**
     * Shows the task completed to the tasklist upon successful marking of a task as Done.
     *
     * @param task the completed task
     * @return description of task marked done
     */
    public static String showDoneTask(Task task) {
        return "Good job! I've marked this task as done: \n" + task;
    }

    /**
     * Shows the number of tasks in the tasklist.
     *
     * @param tasks the tasklist
     * @return description of current number of tasks in list
     */
    public static String showTaskCount(TaskList tasks) {
        return String.format("Now you have %d tasks in your list.%n%n", tasks.count());

    }

    /**
     * Shows the description of error when an operation fails.
     *
     * @param e the exception that caused the error
     * @return description of error
     */
    public static String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Shows the search results by displaying the matching tasks in
     * an indexed list.
     *
     * @param results ArrayList of matching tasks
     * @return indexed list of matching tasks
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

    /**
     * Generates and returns the help message.
     * @return list of commands to use
     */
    public static String showHelpMessage() {
        return "Hi, I'm Duke! To get started, enter your commands.\n\n"
                + "1. To view your task list enter 'list'\n\n"
                + "2. To add a task use the following format: \n"
                + "    'todo <description>'\n"
                + "    'deadline <description> /by <YYYY-MM-DD>'\n"
                + "    'event <description> /at <date or time>'\n\n"
                + "3. To mark a task as done enter\n"
                + "    'done <task no>'\n\n"
                + "4. To delete a task enter\n"
                + "    'delete <task no>'\n\n"
                + "5. To search the task list enter\n"
                + "    'find <keyword>'\n\n"
                + "6. To close the program enter 'bye'\n";
    }
}

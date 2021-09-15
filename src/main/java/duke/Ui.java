package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Class that deals with interacting with the users.
 */
public class Ui {
    protected Scanner scanner;

    /**
     * Class constructor. Initialises a new scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the greeting message and program usage at the start of the program.
     *
     * @return The welcome message.
     */
    public String showStartup() {
        String greeting = "Wassup! I'm ChadBot.\n";
        return greeting + "\n" + showHelp() + showLine();
    }

    /**
     * Returns the app usage menu.
     *
     * @return The app usage menu.
     */
    public String showHelp() {
        String usage = "Usage:\n"
                + "help - show this menu\n"
                + "list - show current tasks\n"
                + "todo [Description] - add todo\n"
                + "deadline [Description] /by [yyyy-mm-dd] - add deadline\n"
                + "event [Description] /at [yyyy-mm-dd HH:mm] - add event\n"
                + "done [Task Number] - mark task as done\n"
                + "find [Keyword] - search for task with word\n"
                + "bye - say goodbye\n";
        return usage;
    }

    /**
     * Returns the user input from the user.
     *
     * @return The user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a line.
     *
     * @return A line separator.
     */
    public String showLine() {
        return "_______________________________________________________\n";
    }

    /**
     * Returns the loading error message.
     */
    public String showLoadingError() {
        return "I can't locate the file!\n";
    }

    /**
     * Returns the error message.
     *
     * @param errorMsg The error message.
     * @return The error message.
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Returns all the current tasks in the TaskList.
     *
     * @param taskList The TaskList object containing all tasks.
     * @return The tasks formatted as a list, or no tasks if the taskList is empty.
     */
    public String printTasks(TaskList taskList) {
        String result;
        if (taskList.isEmpty()) {
            result = "You have no tasks!\n";
        } else {
            result = "Here are your tasks!\n" + taskList.toString();
        }
        return result;
    }

    /**
     * Returns the tasks that match the filter.
     *
     * @param filter The string to search for.
     * @param taskList The taskList to search in.
     * @return The search result formatted, or no task if the search result is empty.
     */
    public String showSearchResult(String filter, TaskList taskList) {
        TaskList searchResult = taskList.findTasks(filter);
        String formattedResult;
        if (searchResult.isEmpty()) {
            formattedResult = "Sorry, there are no tasks that match your search!";
        } else {
            formattedResult = ("Here are the matching tasks in your list:\n"
                    + searchResult.toString());
        }
        return formattedResult;
    }

    /**
     * Returns the task that was successfully added.
     *
     * @param task The task object that was added.
     * @return The task object added to the taskList.
     */
    public String showTaskAdded(Task task) {
        return ("Okay! I've added this task:\n    " + task.toString()) + "\n";
    }

    /**
     * Returns the current number of tasks in the TaskList.
     *
     * @param taskList The TaskList object containing all tasks.
     * @return The current number of tasks in the taskList.
     */
    public String showTaskListSize(TaskList taskList) {
        return String.format("You have %d task(s) in the list.\n\n", taskList.getSize());
    }

    /**
     * Returns the task that was successfully deleted.
     *
     * @param task The task object that was deleted.
     * @return The task object that was removed from the taskList.
     */
    public String showTaskDeleted(Task task) {
        return ("Okay! I've removed this task:\n    " + task.toString());
    }

    /**
     * Returns the task that was marked as done.
     *
     * @param task The task object that was marked done.
     * @return The task object that was marked done.
     */
    public String showTaskMarked(Task task) {
        return ("Okay! This task has been marked done:\n  " + task.toString() + "\n");
    }

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message.
     */
    public String showGoodbye() {
        scanner.close();
        return "Bye bye!\n";
    }
}

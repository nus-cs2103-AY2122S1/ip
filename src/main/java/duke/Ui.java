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
     * Prints the greeting message and program usage at the start of the program.
     */
    public String showStartup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";

        String usage = "Usage:\n"
                + "list                                         - show current tasks\n"
                + "todo [Description]                           - add todo\n"
                + "deadline [Description] /by [yyyy-mm-dd]      - add deadline\n"
                + "event [Description] /at [yyyy-mm-dd HH:mm]   - add event\n"
                + "done [Task Number]                           - mark task as done\n"
                + "find [Description]                           - search for task with word\n"
                + "bye                                          - say goodbye\n";
        return logo + greeting + "\n"
                + usage + showLine();
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
     * Prints a line.
     */
    public String showLine() {
        return "_______________________________________________________\n";
    }

    /**
     * Prints the loading error message.
     */
    public String showLoadingError() {
        return "I can't locate the file!\n";
    }

    /**
     * Prints the error message.
     *
     * @param errorMsg The error message.
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Prints all the current tasks in the TaskList.
     * @param taskList The TaskList object containing all tasks.
     */
    public String printTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks!\n";
        } else {
            return "Here are your tasks!\n" + taskList.toString();
        }
    }

    /**
     * Print the tasks that match the filter.
     *
     * @param filter The string to search for.
     * @param taskList The taskList to search in.
     */
    public String showSearchResult(String filter, TaskList taskList) {
        TaskList searchResult = taskList.findTasks(filter);
        if (searchResult.isEmpty()) {
            return "Sorry, there are no tasks that match your search!";
        } else {
            return ("Here are the matching tasks in your list:\n"
                    + searchResult.toString());
        }
    }

    /**
     * Prints the task that was successfully added.
     *
     * @param task The task object that was added.
     */
    public String taskAdded(Task task) {
        return ("Okay! I've added this task:\n    " + task.toString()) + "\n";
    }

    /**
     * Prints the current number of tasks in the TaskList.
     *
     * @param taskList The TaskList object containing all tasks.
     */
    public String showTaskListSize(TaskList taskList) {
        return String.format("You have %d task(s) in the list.\n\n", taskList.getSize());
    }

    /**
     * Prints the task that was successfully deleted.
     *
     * @param task The task object that was deleted.
     */
    public String taskDeleted(Task task) {
        return ("Okay! I've removed this task:\n    " + task.toString());
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task The task object that was marked done.
     */
    public String taskMarked(Task task) {
        return ("Okay! This task has been marked done:\n  " + task.toString() + "\n");
    }

    /**
     * Prints the goodbye message.
     */
    public String showGoodbye() {
        scanner.close();
        return "Bye bye!\n";
    }
}

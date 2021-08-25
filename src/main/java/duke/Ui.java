package duke;

import duke.task.Task;

import java.util.Scanner;

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
    public void showStartup() {
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
                + "bye                                          - say goodbye\n";
        System.out.println(logo + greeting + "\n"
                + usage);
        showLine();
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
    public void showLine() {
        System.out.println("_______________________________________________________\n");
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("I can't locate the file!\n");
    }

    /**
     * Prints the error message.
     *
     * @param errorMsg The error message.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints all the current tasks in the TaskList.
     * @param taskList The TaskList object containing all tasks.
     */
    public void printTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks!\n");
        } else {
            System.out.println("Here are your tasks!\n" + taskList.toString());
        }
    }

    /**
     * Print the tasks that match the filter.
     *
     * @param filter The string to search for.
     * @param taskList The taskList to search in.
     */
    public void showSearchResult(String filter, TaskList taskList) {
        TaskList searchResult = taskList.findTasks(filter);
        if (searchResult.isEmpty()) {
            System.out.println("Sorry, there are no tasks that match your search!");
        } else {
            System.out.println("Here are the matching tasks in your list:\n"
                    + searchResult.toString());
        }
    }

    /**
     * Prints the task that was successfully added.
     *
     * @param task The task object that was added.
     */
    public void taskAdded(Task task) {
        System.out.println("Okay! I've added this task:\n    " + task.toString());
    }

    /**
     * Prints the current number of tasks in the TaskList.
     *
     * @param taskList The TaskList object containing all tasks.
     */
    public void showTaskListSize(TaskList taskList){
        System.out.printf("You have %d task(s) in the list.\n\n", taskList.getSize());
    }

    /**
     * Prints the task that was successfully deleted.
     *
     * @param task The task object that was deleted.
     */
    public void taskDeleted(Task task) {
        System.out.println("Okay! I've removed this task:\n    " + task.toString());
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task The task object that was marked done.
     */
    public void taskMarked(Task task) {
        System.out.println("Okay! This task has been marked done:");
        System.out.println("  " + task.toString() + "\n");
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye bye!\n");
        scanner.close();
    }
}

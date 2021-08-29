package duke.ui;

import java.util.Scanner;

/**
 * Represents a user interface (command prompt line) of chat bot.
 */
public class Ui {
    /**
     * Displays large logo and welcome message.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        String greet = "____________________________________________________________\n"
                     + "Hello! I'm Duke\n"
                     + "What can I do for you?\n"
                     + "____________________________________________________________";

        return logo + greet;
    }

    /**
     * Displays loading error message.
     */
    public String showLoadingError() {
        return  "An error occurred when loading file.";
    }

    /**
     * Displays goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays error message.
     */
    public String showError(String message) {
        return "☹ OOPS!!! " + message;
    }

    /**
     * Displays a line.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns user input of command line.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays new task added message.
     *
     * @param task task added.
     * @param size total size of TaskList.
     */
    public String showAdded(String task, int size) {
        return String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.", task, size);
    }

    /**
     * Displays task removed message.
     *
     * @param task task added.
     * @param size total size of TaskList.
     */
    public String showDelete(String task, int size) {
        return String.format("Got it. I've deleted this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, size);
    }

    /**
     * Displays list of tasks found.
     *
     * @param tasks list of task found.
     */
    public String showFind(String tasks) {
        return String.format("Here are the matching tasks in your list:\n%s", tasks);
    }

    /**
     * Displays list of tasks.
     *
     * @param tasks list of task.
     */
    public String showList(String tasks) {
        return String.format("Here are the tasks in your list:\n%s", tasks);
    }

    /**
     * Displays task completed.
     *
     * @param task task completed.
     */
    public String showDone(String task) {
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }
}

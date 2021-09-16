package duke.ui;

import java.util.Scanner;

/**
 * Represents a user interface (command prompt line) of chat bot.
 */
public class Ui {
    /**
     * Displays large logo and welcome message.
     *
     * @return string of logo
     */
    public String showWelcome() {
        return "Hello! I'm Jarvis\n"
             + "What can I do for you?\n";
    }

    /**
     * Displays goodbye message.
     *
     * @return goodbye message
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns user input of command line.
     *
     * @return user's input
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
        if (tasks.equals("")) {
            return "No match found!";
        }
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
     * Displays sorted list of tasks.
     *
     * @param tasks list of sorted task.
     */
    public String showSortedList(String tasks) {
        if (tasks.equals("")) {
            return "No match found!";
        }
        return String.format("Here are the sorted task in your list:\n%s", tasks);
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

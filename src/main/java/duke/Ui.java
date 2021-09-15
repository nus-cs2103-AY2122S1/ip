package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface which handles input and output.
 */
public class Ui {
    private static final String LOGO =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner input = new Scanner(System.in);

    /**
     * Read the input from the user.
     *
     * @return string representation of user's input in the CLI
     */
    public String readInput() {
        return this.input.nextLine();
    }


    /**
     * Close the input scanner.
     */
    public void closeInput() {
        this.input.close();
    }


    /**
     * Show greeting message to the user.
     *
     * @return the greeting message
     */
    public String showGreet() {
        return String.format(
                "%s\n"
                + "Welcome! I'm Duke.\n"
                + "What can I do for you?\n", Ui.LOGO
        );
    }


    /**
     * Show farewell message to the user.
     *
     * @return the farewell message
     */
    public String showFarewell() {
        return "Bye, hope to see you again!";
    }


    /**
     * Show the details of addition of a task to the list.
     *
     * @param task task that has been added to the task list
     * @param totalNumTask total number of tasks in the task list
     * @return message that shows the added task
     */
    public String showAdd(Task task, int totalNumTask) {
        return String.format(
                "added:\n\t%s\n"
                + "You have %d tasks in the list.\n", task, totalNumTask
        );
    }


    /**
     * Show the details of deletion of a task from the list.
     *
     * @param task task that has been deleted from the task list
     * @param totalNumTask total number of tasks in the task list
     * @return message that shows the deletion of the task
     */
    public String showDelete(Task task, int totalNumTask) {
        return String.format(
                "I've deleted this task from the list!\n"
                + "\t%s\nYou have %d tasks in the list.\n",
                task, totalNumTask - 1
        );
    }


    /**
     * Show the details of a task being marked as done.
     *
     * @param task task that has been marked as done
     * @return message that shows a specific task being marked done
     */
    public String showDone(Task task) {
        return String.format(
                "I've marked this task as done!\n"
                + "\t%s\n", task
        );
    }


    /**
     * Show the entire list of tasks.
     *
     * @param taskList task list in the duke instance
     * @return message that shows the entire list
     */
    public String showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no task in your list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= taskList.getLength(); i++) {
            sb.append(String.format("\t%d. %s\n", i, taskList.get(i)));
        }
        return "Here are the tasks in your list:\n" + sb;
    }


    /**
     * Show the result when searching for a keyword.
     *
     * @param taskList task list in the duke instance
     * @param keyword keyword to be searched upon
     * @return message that shows the search result
     */
    public String showSearchResult(TaskList taskList, String keyword) {
        if (taskList.isEmpty()) {
            return "You have no task in your list.\n";
        }

        TaskList filteredList = taskList.filter(keyword);
        if (filteredList.isEmpty()) {
            return "You have no matching task in your list.\n";
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= filteredList.getLength(); i++) {
            sb.append(String.format("\t%d. %s\n", count++, filteredList.get(i)));
        }
        return "Here are the matching tasks in your list:\n" + sb;
    }


    /**
     * Show details of a specific duke exception
     *
     * @param e the duke exception that was being thrown
     * @return the exception message for DukeException
     */
    public String showDukeException(DukeException e) {
        return String.format("%s\n", e);
    }
}

package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface which handles input and output.
 */
public class Ui {
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
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return String.format(
                "%s\n" +
                "Welcome! I'm Duke.\n" +
                "What can I do for you?\n", logo
        );
    }


    /**
     * Show farewell message to the user.
     *
     * @return the farewell message
     */
    public String showFarewell() {
        return "\tBye, hope to see you again!";
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
                "\tadded:\n\t\t%s\n" +
                "\tYou have %d tasks in the list.\n", task, totalNumTask
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
                "\tI've deleted this task from the list!\n" +
                "\t\t%s\n\tYou have %d tasks in the list.\n",
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
                "\tI've marked this task as done!\n" +
                "\t\t%s\n", task
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
            return "\tYou have no task in your list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= taskList.getLength(); i++) {
            sb.append(String.format("\t%d. %s\n", i, taskList.get(i)));
        }
        return "\tHere are the tasks in your list:\n" + sb;
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
            return "\tYou have no task in your list.\n";
        }

        TaskList filteredList = taskList.filter(keyword);
        if (filteredList.isEmpty()) {
            return "\tYou have no matching task in your list.\n";
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= filteredList.getLength(); i++) {
            sb.append(String.format("\t%d. %s\n", count++, filteredList.get(i)));
        }
        return "\tHere are the matching tasks in your list:\n" + sb;
    }


    /**
     * Display to users that error was thrown while saving data to the database.
     *
     * @return the error message
     */
    public String showSavingError() {
        return "Error when saving data!";
    }


    /**
     * Display to user that their date is of invalid format.
     *
     * @return the error message
     */
    public String showInvalidDateFormat() {
        return "\tPlease enter the start/end time in the format of <DD/MM/YY HH:MM>!\n";
    }


    /**
     * Display to user that their date is of invalid range.
     *
     * @return the error message
     */
    public String showInvalidDateRange() {
        return "\tEnd time must be after the start time!\n";
    }


    /**
     * Show details of a specific duke exception
     *
     * @param e the duke exception that was being thrown
     * @return the exception message for DukeException
     */
    public String showDukeException(DukeException e) {
        return String.format("\t%s\n", e);
    }
}

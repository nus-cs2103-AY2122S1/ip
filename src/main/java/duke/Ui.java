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
     */
    public void showGreet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    /**
     * Show farewell message to the user.
     */
    public void showFarewell() {
        System.out.println("\tBye, hope to see you again!");
    }


    /**
     * Show the details of addition of a task to the list.
     *
     * @param task task that has been added to the task list
     * @param totalNumTask total number of tasks in the task list
     */
    public void showAdd(Task task, int totalNumTask) {
        System.out.printf("\tadded:\n\t\t%s\n", task);
        System.out.printf("\tYou have %d tasks in the list.\n\n", totalNumTask);
    }


    /**
     * Show the details of deletion of a task from the list.
     *
     * @param task task that has been deleted from the task list
     * @param totalNumTask total number of tasks in the task list
     */
    public void showDelete(Task task, int totalNumTask) {
        System.out.println("\tI've deleted this task from the list!");
        System.out.printf("\t\t%s\n", task);
        System.out.printf("\tYou have %d tasks in the list.\n\n", totalNumTask - 1);
    }


    /**
     * Show the details of a task being marked as done.
     *
     * @param task task that has been marked as done
     */
    public void showDone(Task task) {
        System.out.println("\tI've marked this task as done!");
        System.out.printf("\t\t%s\n\n", task);
    }


    /**
     * Show the entire list of tasks.
     *
     * @param taskList task list in the duke instance
     */
    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tYou have no task in your list.\n");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= taskList.getLength(); i++) {
            System.out.println("\t" + i + ". " + taskList.get(i));
        }
        System.out.println();
    }


    /**
     * Display to users that error was thrown while saving data to the database.
     */
    public void showSavingError() {
        System.out.println("Error when saving data!");
    }


    /**
     * Display to user that their date is of invalid format.
     */
    public void showInvalidDateFormat() {
        System.out.println("\tPlease enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
    }


    /**
     * Display to user that their date is of invalid range.
     */
    public void showInvalidDateRange() {
        System.out.println("\tEnd time must be after the start time!\n");
    }


    /**
     * Show details of a specific duke exception
     *
     * @param e the duke exception that was being thrown
     */
    public void showDukeException(DukeException e) {
        System.out.printf("\t%s\n\n", e);
    }
}
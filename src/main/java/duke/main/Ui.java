package duke.main;

import duke.task.Task;
import java.util.Scanner;

/**
 * The Ui class handles the reading of user input and outputting messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Class Constructor which initializes the scanner field.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method that utilizes the scanner to read the user input.
     *
     * @return String The user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Method that displays the greeting message.
     */
    public void greet() {
        System.out.println("\t Hello! I'm Duke");
    }

    /**
     * Method that displays any error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Method that displays a message that informs user that a task has been successfully added and displays the number
     * of tasks in the TaskList
     *
     * @param t The newly added Task.
     * @param tasks The current list of Task.
     */
    public void displayAddedMessage(Task t, TaskList tasks) {
        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        System.out.println("\t There are " + tasks.getSize() + " items in the list");
    }

    /**
     * Method that displays a message that informs user that the task has been marked as done.
     *
     * @param taskDone The task that has been marked as done.
     */
    public void displayCompletedMessage(Task taskDone) {
        System.out.println("\t Good job! This task has been completed:");
        System.out.println("\t \t" + taskDone.toString());
    }

    /**
     * Method that displays all the current Task in the TaskList.
     *
     * @param tasks The list of Task inputted by the user.
     */
    public void displayListOfTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Method that displays message when user exits program.
     */
    public void displayExitMessage() {
        System.out.println("\t Bye. Hope to see you again soon");
    }

    /**
     * Method that displays message to inform user that their list has been saved.
     */
    public void displaySaveMessage() {
        System.out.println("\t List successfully saved");
    }

    /**
     * Method that displays message informing user that the task indicated has been removed.
     *
     * @param taskList The list of Task.
     * @param index The index of the Task deleted.
     */
    public void displayDeleteMessage(TaskList taskList, int index) {
        System.out.println("\t Noted. The task has been removed!");
    }

}

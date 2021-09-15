package duke.main;

import java.util.Scanner;

import duke.task.Task;

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
     * Utilizes the scanner to read the user input and return the user input.
     * @return String The user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Returns a greeting message.
     * @return String The greeting message.
     */
    public String greet() {
        return ("Hello! I'm Duke");
    }

    /**
     * Returns an error message.
     * @param error The error that occurred.
     * @return String The error message.
     */
    public String displayError(DukeException error) {
        return error.getMessage();
    }

    /**
     * Returns a message that informs user that a task has been successfully added and displays the number
     * of tasks in the TaskList
     * @param t The newly added Task.
     * @param tasks The current list of Task.
     * @return String The successfully added message.
     */
    public String displayAddedMessage(Task t, TaskList tasks) {
        assert t != null : "Task added should not be null";
        assert tasks != null : "TaskList should have been initialized";
        String s = "I have added to the list: \n\t \t " + t.toString()
                + ". There are " + tasks.getSize() + " items in the list";
        return s;
    }

    /**
     * Returns a message that informs user that the task has been marked as done.
     * @param taskDone The task that has been marked as done.
     * @return String The successfully completed message.
     */
    public String displayCompletedMessage(Task taskDone) {
        assert taskDone != null : "Task to be marked as done should not be null";
        String s = "Good job! This task has been completed: \n" + "\t \t" + taskDone.toString();
        return s;
    }

    /**
     * Returns all the current Task in the TaskList in a String.
     * @param tasks The list of Task inputted by the user.
     * @return String The String representation of the list of Task.
     */
    public String displayListOfTasks(TaskList tasks) {
        assert tasks != null : "TaskList should have been initialized";
        return tasks.toString();
    }

    /**
     * Returns exit message when user exits program.
     * @return String The exit message.
     */
    public String displayExitMessage() {
        return "Bye. Hope to see you again soon";
    }

    /**
     * Returns message to inform user that their list has been saved.
     * @return String The save message.
     */
    public String displaySaveMessage() {
        return "List successfully saved";
    }

    /**
     * Returns message informing user that the task indicated has been removed.
     * @return String The successfully deleted message.
     */
    public String displayDeleteMessage() {
        return " Noted. The task has been removed!";
    }

    /**
     * Returns a message that indicates that the priority has changed.
     * @param task The task that has been changed.
     * @return The successful priority changed message.
     */
    public String displayChangedPriorityMessage(Task task) {
        assert task != null : "Task to be marked as done should not be null";
        String s = "The priority for this task has been changed: \n" + "\t \t" + task.toString();
        return s;
    }
}

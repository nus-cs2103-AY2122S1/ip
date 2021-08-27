package duke.ui;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the user interface of Duke. Deals with Duke's interaction with the user.
 */
public class Ui {

    private static final String line =
            "------------------------------------------------------------------------------"
                    + "-------------------------------";

    /**
     * Returns the response in a standardised format.
     *
     * @param response Duke's response to the user.
     * @return Duke's response in a standardised format.
     */
    private String wrap(String response) {
        return line
                + "\n\n"
                + "\t" + response
                + "\n\n"
                + line
                + "\n";
    }

    public Ui() {}

    /**
     * Prints a greeting message.
     *
     * @param savedTasks Previously saved tasks taken from storage.
     */
    public void greeting(String savedTasks) {
        System.out.println(wrap("Hello! I'm Duke!\n\n"
                + "\t" + savedTasks + "\n\n"
                + "\tWhat can I do for you?")
        );
    }

    /**
     * Prints a farewell message.
     */
    public void farewell() {
        System.out.println(wrap("Bye. Hope to see you again soon!"));
    }

    /**
     * Prints a response.
     *
     * @param response Duke's response to the user input.
     */
    public void reply(String response) {
        System.out.println(wrap(response));
    }

    /**
     * Prints a done message.
     * When user inputs a done instruction.
     *
     * @param task The task marked as done.
     */
    public void doneMsg(Task task) {
        System.out.println(wrap("Nice! I've marked this task as done:\n"
                + "\t\t" + task.toString()));
    }

    /**
     * Prints a delete message.
     * When user inputs a delete instruction.
     *
     * @param task The task deleted.
     * @param taskCount The remaining number of tasks in the task list.
     */
    public void deleteMsg(Task task, int taskCount) {
        System.out.println(wrap("Noted. I've removed this task: \n"
                + "\t\t" + task.toString() + "\n"
                + "\tNow you have " + taskCount + " tasks in the list."));
    }

    /**
     * Prints a add task message.
     * When user inputs a add task instruction.
     *
     * @param task The task added.
     * @param taskCount The current number of tasks in the task list.
     */
    public void addTaskMsg(Task task, int taskCount) {
        System.out.println(wrap("Got it. I've added this task:\n"
                + "\t\t" + task.toString() + "\n"
                + "\t" + "Now you have " + taskCount + " tasks in the list."));
    }

    /**
     * Prints a message showing matching tasks.
     *
     * @param tasks Matching tasks to be printed.
     */
    public void matchingTasksMsg(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(wrap("There are no matching tasks in your list."));
        } else {
            String text = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                text += "\n\t" + tasks.get(i).toString();
            }
            System.out.println(wrap(text));
        }
    }

    /**
     * Prints an invalid input message.
     * When the user enters an invalid input.
     *
     * @param e The InvalidInputException.
     */
    public void invalidInput(InvalidInputException e) {
        System.out.println(wrap(e.toString()));
    }

    /**
     * Prints an invalid instruction message.
     * When the user enters an invalid instruction.
     *
     * @param e The InvalidInputException.
     */
    public void invalidInstruction(InvalidInstructionException e) {
        System.out.println(wrap(e.toString()));
    }

    /**
     * Prints an exception message.
     * When an exception that is not a Duke exception is thrown.
     *
     * @param e The non-Duke Exception.
     */
    public void printException(Exception e) {
        System.out.println(wrap(e.toString()));
    }
}

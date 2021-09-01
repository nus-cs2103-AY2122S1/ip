package duke;

import duke.task.Task;

/**
 * Ui class that encapsulates the interface which the user interacts with.
 */
public class Ui {

    /**
     * Prints a standard formatted message.
     *
     * @param message Message to be printed in console.
     */
    public String display(String message) {
        return message + "\n";
    }

    /**
     * Prints exit message.
     */
    public String exit() {
        return display("Goodbye. See you again soon!");
    }

    /**
     * Prints success message for adding task.
     *
     * @param task     The individual task which can be Todo, Deadline or Event.
     * @param numTasks The number of tasks in the list.
     */
    public String displaySuccessMessage(Task task, int numTasks) {
        String msg = "Got it. I've added this task: \n" + "      " + task + "\n    Now you have "
                + numTasks + (numTasks <= 1 ? " task" : " tasks") + " in the list.";
        return msg;
    }


}

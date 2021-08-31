package duke.util;

import java.util.Scanner;

import duke.task.Task;

/**
 * A medium between the user and Duke to communicate.
 * Scans for user inputs and format Duke's response before printing to screen.
 */
public class Ui {
    /**
     * Instantiates a ui instance to be used for receiving user input.
     */
    public Ui() {

    }

    /**
     * Format message and print to screen.
     *
     * @param message message to be displayed.
     * @return message to be sent back to user.
     */
    public String respond(String message) {
        return message;
    }

    /**
     * Format response for find command.
     *
     * @param tasks array of task to be printed.
     * @return greetings message.
     */
    public String findResponse(Task[] tasks) {
        StringBuilder res = new StringBuilder();
        res.append("Found ").append(tasks.length).append(" tasks");
        for (Task task : tasks) {
            res.append("\n").append(task.toString());
        }
        return res.toString();
    }

    /**
     * @return  greetings message.
     */
    public String greet() {
        return "How can I help?";
    }

    /**
     * @return a prompt for user to send next command.
     */
    public String promptNext() {
        return "Whats next?";
    }

    /**
     * @return an exit message.
     */
    public String exitMessage() {
        return "Ooooh yeah! Can do!";
    }
}

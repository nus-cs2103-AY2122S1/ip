package main.java;

import java.time.format.DateTimeFormatter;

/**
 * Task class that encapsulates the individual task passed into the bot.
 */
public class Task {

    /**
     * Variable that holds the task input as String
     */
    protected String task;

    /**
     * Variable that holds the task completion state as boolean
     */
    protected boolean done;

    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy, hh:mm a");

    /**
     * Constructor for the Task class
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    public String getTaskState() {
        return this.done ? "[X] " + this.task : "[ ] " + this.task;
    }

    /**
     * Toggles the current state by negating the boolean state value. (Done vs not done)
     */
    public void markAsDone() {
        this.done = true;
    }
}

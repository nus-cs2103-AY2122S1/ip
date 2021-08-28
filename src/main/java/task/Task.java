package main.java.task;

import java.time.format.DateTimeFormatter;

/**
 * Task class that encapsulates the individual main.java.task passed into the main.java.bot.
 */
public class Task {

    /**
     * Variable that holds the main.java.task input as String
     */
    protected String task;

    /**
     * Variable that holds the main.java.task completion state as boolean
     */
    protected boolean done;

    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    /**
     * Constructor for the Task class
     * Default: not done
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructor for the Task class
     * According to specified state
     */
    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Retrieves the completion state of the main.java.task, followed by the main.java.task input.
     *
     * @return The String representation of the main.java.task completion state and the main.java.task input.
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

    public static Task parseFromStorage(String task) {
        String[] splitTask = task.split(",");
        String taskType = splitTask[0];

        switch (taskType) {
            case "T":
                return new TodoTask(splitTask[2], splitTask[1].equals("1"));
            case "D":
                return new DeadlineTask(splitTask[2], splitTask[3], splitTask[1].equals("1"));
            case "E":
                return new EventTask(splitTask[2], splitTask[3], splitTask[1].equals("1"));
            default:
                return new Task("");
        }
    }

    public String convertToStorageFormat() {
        return "";
    }
}

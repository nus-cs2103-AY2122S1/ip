package main.java.task;

import java.time.format.DateTimeFormatter;

/**
 * A class that encapsulates a general Task stored by Duke.
 */
public class Task {

    protected String task;
    protected boolean done;
    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    /**
     * Constructor for the Task class.
     *
     * @param task The task to be stored within this Task object.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructor for the Task class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this Task object.
     * @param done The state of the Task object.
     */
    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Returns the String representation of the Task object, showing the state and the task.
     *
     * Takes no parameters.
     *
     * @return A String enumerating this Task object.
     */
    public String getTaskState() {
        return this.done ? "[X] " + this.task : "[ ] " + this.task;
    }

    /**
     * Sets the current task state as completed.
     *
     * Takes no parameters.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Returns the Task object that has been parsed from the given string.
     * A static method.
     *
     * @param task The String serialisation of the task from local data.
     * @return A new Task object initialised from given local data.
     */
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

    /**
     * Returns the storage format of this Task object.
     *
     * Takes no parameters.
     */
    public String convertToStorageFormat() {
        return "";
    }
}

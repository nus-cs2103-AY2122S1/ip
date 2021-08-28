package main.java.task;

import java.time.LocalDateTime;

/**
 * A class that encapsulates a Deadline Task stored by Duke.
 */
public class DeadlineTask extends Task {

    protected LocalDateTime time;
    protected String storedTime = "";

    /**
     * Constructor for the DeadlineTask class.
     *
     * @param task The task to be stored within this DeadlineTask object.
     * @param time The LocalDateTime object representation of the Deadline "by".
     */
    public DeadlineTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the DeadlineTask class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this DeadlineTask object.
     * @param storedTime The String representation of the Deadline "by".
     * @param state The state of the DeadlineTask object.
     */
    public DeadlineTask(String task, String storedTime, boolean state) {
        super(task, state);
        this.storedTime = storedTime;
    }

    /**
     * Returns the String representation of the DeadlineTask object, showing the state and the task.
     *
     * Takes no parameters.
     *
     * @return A String enumerating this Task object.
     */
    @Override
    public String getTaskState() {
        return "[D]" + super.getTaskState() + "(By: " + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime) + ")";
    }

    /**
     * Returns the storage format of this DeadlineTask object.
     *
     * Takes no parameters.
     */
    @Override
    public String convertToStorageFormat() {
        return "D,"
                + (state ? "1," : "0,")
                + task
                + ","
                + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime);
    }
}

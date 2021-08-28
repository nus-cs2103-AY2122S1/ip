package main.java.task;

import java.time.LocalDateTime;

/**
 * A class that encapsulates an Event Task stored by Duke.
 */
public class EventTask extends Task {

    protected LocalDateTime time;
    protected String storedTime = "";

    /**
     * Constructor for the EventTask class.
     *
     * @param task The task to be stored within this EventTask object.
     * @param time The LocalDateTime object representation of the Event "at".
     */
    public EventTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the EventTask class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this EventTask object.
     * @param storedTime The String representation of the Event "at".
     * @param state The state of the EventTask object.
     */
    public EventTask(String task, String storedTime, boolean state) {
        super(task, state);
        this.storedTime = storedTime;
    }

    /**
     * Returns the String representation of the EventTask object, showing the state and the task.
     *
     * Takes no parameters.
     *
     * @return A String enumerating this Task object.
     */
    @Override
    public String getTaskState() {
        return "[E]" + super.getTaskState() + "(At: " + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime) + ")";
    }

    /**
     * Returns the storage format of this EventTask object.
     *
     * Takes no parameters.
     */
    @Override
    public String convertToStorageFormat() {
        return "E,"
                + (state ? "1," : "0,")
                + task
                + ","
                + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime);
    }
}

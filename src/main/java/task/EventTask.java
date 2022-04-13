package task;

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
     *
     * @param task The task to be stored within this EventTask object.
     * @param time The LocalDateTime object representation of the Event "at".
     * @param tags The tags to be stored within this EventTask object.
     */
    public EventTask(String task, LocalDateTime time, String tags) {
        super(task, tags);
        this.time = time;
    }

    /**
     * Constructor for the EventTask class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this EventTask object.
     * @param storedTime The String representation of the Event "at".
     * @param tags The tags to be stored within this EventTask object.
     * @param isDone The state of the EventTask object.
     */
    public EventTask(String task, String storedTime, String tags, boolean isDone) {
        super(task, tags, isDone);
        this.storedTime = storedTime;
    }

    /**
     * Returns the String representation of the "At" time stored within this EventTask object.
     *
     * @return A String enumerating the "At" time of this Event.
     */
    public String getTime() {
        if (storedTime.isEmpty()) {
            return time.format(OUTPUT_FORMATTER);
        } else {
            return storedTime;
        }
    }

    /**
     * Returns the type of this Task object.
     *
     * @return A String corresponding to the type of this Task object.
     */
    @Override
    public String getTaskType() {
        return super.getTaskType() + " (Event)";
    }

    /**
     * Returns the String representation of the EventTask object, showing the state and the task.
     *
     * @return A String enumerating this EventTask object.
     */
    @Override
    public String getTaskState() {
        return "[E]" + super.getTaskState() + " (At: " + this.getTime() + ")" + "\n" + this.tags.toString().trim();
    }

    /**
     * Returns the storage format of this EventTask object.
     *
     * @return A String representation of the EventTask object, formatted for storage inside local hard disk.
     */
    @Override
    public String convertToStorageFormat() {
        return "E,"
                + (isDone ? "1," : "0,")
                + task
                + ","
                + tags.toString()
                + ","
                + this.getTime()
                + ",";
    }
}

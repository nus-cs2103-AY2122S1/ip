package task;

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
     *
     * @param task The task to be stored within this DeadlineTask object.
     * @param time The LocalDateTime object representation of the Deadline "by".
     * @param tags The tags to be stored within this DeadlineTask object.
     */
    public DeadlineTask(String task, LocalDateTime time, String tags) {
        super(task, tags);
        this.time = time;
    }

    /**
     * Constructor for the DeadlineTask class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this DeadlineTask object.
     * @param storedTime The String representation of the Deadline "by".
     * @param tags The tags to be stored within this DeadlineTask object.
     * @param isDone The state of the DeadlineTask object.
     */
    public DeadlineTask(String task, String storedTime, String tags, boolean isDone) {
        super(task, tags, isDone);
        this.storedTime = storedTime;
    }

    /**
     * Returns the String representation of the "By" time stored within this DeadlineTask object.
     *
     * @return A String enumerating the "By" time of this Deadline.
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
        return super.getTaskType() + " (Deadline)";
    }

    /**
     * Returns the String representation of the DeadlineTask object, showing the state and the task.
     *
     * @return A String enumerating this DeadlineTask object.
     */
    @Override
    public String getTaskState() {
        return "[D]" + super.getTaskState() + " (By: " + this.getTime() + ")" + "\n" + this.tags.toString().trim();
    }

    /**
     * Returns the storage format of this DeadlineTask object.
     *
     * @return A String representation of the DeadlineTask object, formatted for storage inside local hard disk.
     */
    @Override
    public String convertToStorageFormat() {
        return "D,"
                + (isDone ? "1," : "0,")
                + task
                + ","
                + tags.toString()
                + ","
                + this.getTime()
                + ",";
    }
}

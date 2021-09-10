package pika.task;

/**
 * Event Class to handle event tasks.
 */
public class Event extends Task {
    protected String eventTime;

    /**
     * Constructor for the event class.
     *
     * @param name      Name of the event Task
     * @param eventTime Date or Date and Time for the event Task
     */
    public Event(String name, String eventTime) { //Event class to handle Event task
        super(name);
        this.eventTime = TaskTime.convertDateTimeFormat(eventTime);
    }

    /**
     * Returns the string to be written in the txt file.
     *
     * @return txt format of the task for storage
     */
    @Override
    public String write() {
        return "E " + super.write() + " | " + this.eventTime + " |" + getTags();
    }

    /**
     * Returns the list format of the task.
     *
     * @return list format of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}

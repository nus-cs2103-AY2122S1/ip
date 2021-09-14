package duke.task;

import duke.exception.TimedTaskDateInputException;

/**
 * The Event type task.
 */
public class Event extends TimedTask {

    /**
     * The constructor of Event.
     * @param name Name of task
     * @param by Date of task
     * @param isCompleted Completion status of task
     * @throws TimedTaskDateInputException if date is the wrong format.
     */
    public Event(String name, String by, boolean isCompleted) throws TimedTaskDateInputException {
        super(name, by, TaskType.E, isCompleted);
    }

    @Override
    protected String getDateTimeString() {
        return " (at: " + this.getDateTime() + ")";
    }

    /**
     * Returns a new Event with the updated datetime.
     * @param dateTime the new dateTime string to be provided.
     * @return new Event with the updated dateTime
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task updateDateTime(String dateTime) throws TimedTaskDateInputException {
        return new Event(this.getName(), dateTime, this.getCompleted());
    }

    /**
     * Returns a new Event with the updated name.
     * @param name the new string to be provided.
     * @return new Event with the updated name
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task updateName(String name) throws TimedTaskDateInputException {
        return new Event(name, this.getDateTimeInternal(), this.getCompleted());
    }

    /**
     * Returns a new Event which is completed.
     * @return new Event which is completed.
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task complete() throws TimedTaskDateInputException {
        return new Event(this.getName(), this.getDateTimeInternal(), true);
    }
}

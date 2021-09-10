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

    @Override
    public Task updateDateTime(String dateTime) throws TimedTaskDateInputException {
        return new Event(this.getName(), dateTime, this.getCompleted());
    }

    @Override
    public Task updateName(String input) throws TimedTaskDateInputException {
        return new Event(input, this.getDateTimeInternal(), this.getCompleted());
    }

    @Override
    public Task complete() throws TimedTaskDateInputException {
        return new Event(this.getName(), this.getDateTimeInternal(), true);
    }
}

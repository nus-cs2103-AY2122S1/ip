package duke.task;

import duke.exception.TimedTaskDateInputException;

/**
 * The Deadline type task.
 */
public class Deadline extends TimedTask {

    /**
     * The constructor of Deadline.
     * @param name Name of task
     * @param by Date of task
     * @param isCompleted Completion status of task
     * @throws TimedTaskDateInputException if date is the wrong format.
     */
    public Deadline(String name, String by, boolean isCompleted) throws TimedTaskDateInputException {
        super(name, by, TaskType.D, isCompleted);
    }

    @Override
    protected String getDateTimeString() {
        return " (by: " + this.getDateTime() + ")";
    }

    /**
     * Returns a new Deadline with the updated datetime.
     * @param dateTime the new dateTime string to be provided.
     * @return new Deadline with the updated dateTime
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task updateDateTime(String dateTime) throws TimedTaskDateInputException {
        return new Deadline(this.getName(), dateTime, this.getCompleted());
    }

    /**
     * Returns a new Deadline with the updated name.
     * @param name the new string to be provided.
     * @return new Deadline with the updated name
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task updateName(String name) throws TimedTaskDateInputException {
        return new Deadline(name, this.getDateTimeInternal(), this.getCompleted());
    }

    /**
     * Returns a new Deadline which is completed.
     * @return new Deadline which is completed.
     * @throws TimedTaskDateInputException if the datetime string is not of the correct format.
     */
    @Override
    public Task complete() throws TimedTaskDateInputException {
        return new Deadline(this.getName(), this.getDateTimeInternal(), true);
    }
}

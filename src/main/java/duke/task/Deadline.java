package duke.task;

import duke.DukeException;

/**
 * The Deadline type task.
 */
public class Deadline extends TimedTask {

    /**
     * The constructor of Deadline.
     * @param name Name of task
     * @param by Date of task
     * @param isCompleted Completion status of task
     * @throws DukeException if date is the wrong format.
     */
    public Deadline(String name, String by, boolean isCompleted) throws DukeException {
        super(name, by, TaskType.D, isCompleted);
    }

    @Override
    protected String getDateTimeString() {
        return " (by: " + this.getDateTime() + ")";
    }

    @Override
    public Task updateName(String input) {
        try {
            return new Deadline(input, this.getDateTimeInternal(), this.getCompleted());
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            return Task.emptyTask();
        }
    }

    @Override
    public Task complete() {
        try {
            return new Deadline(this.getName(), this.getDateTimeInternal(), true);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            return Task.emptyTask();
        }
    }
}

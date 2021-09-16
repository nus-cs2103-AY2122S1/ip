package duke.task;
import duke.DukeException;
import duke.Ui;

/**
 * Class that encapsulates Tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Ui ui = new Ui();

    /**
     * Constructor for Task.
     *
     * @param description Task description.
     */
    public Task(String description) throws DukeException {
        if (description.equals("") || description.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            this.description = description.substring(1);
        }
        this.isDone = false;
    }

    /**
     * Represents undone tasks with a space and done tasks with an "X".
     *
     * @return " " for undone tasks and "X" for done tasks.
     */
    public String getStatusIcon() {
        return ui.taskStatusIcon(isDone);
    }

    /**
     * Returns a String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    /**
     * Returns the task description.
     *
     * @return Task description
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Sets tasks as done by setting the isDone boolean to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Checks the status of the task by checking the status of the isDone boolean.
     *
     * @return True of the task is done, false if the task is undone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns additional dates (if any).
     *
     * @return Additional dates.
     */
    public String additionalDates() {
        return "";
    }
}

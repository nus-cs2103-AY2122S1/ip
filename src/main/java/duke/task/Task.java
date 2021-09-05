package duke.task;

import duke.DukeException;

/**
 * Abstract class that represent task.
 */
public abstract class Task {
    private String description;
    private boolean done = false;
    public static final String SEP = "4%213";

    protected Task(String description) {
        this.description = description;
    }

    /**
     * Makes a task based on the input.
     *
     * @param type type of command.
     * @param description description of the command.
     * @return task based on input.
     * @throws DukeException if input is invalid.
     */
    public static Task makeTask(String type, String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("The description cannot be empty");
        }
        switch (type) {
            case "todo": {
                return (ToDo.of(description));
            }
            case "deadline": {
                return (Deadline.of(description));
            }
            case "event": {
                return (Event.of(description));
            }
            default: {
                throw new DukeException("Sorry I don't understand");
            }
        }
    }


    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        String doneIndicator = this.done
                ? "[X]"
                : "[ ]";
        return (doneIndicator + " " + this.description);
    }

    /**
     * Returns string representation of the task to be saved in the hard disk.
     *
     * @param time time of the task.
     * @return string representation of the task to be saved in the hard disl/
     */
    protected String toSaveInFile(String time) {
        String doneString = this.done ? "1" : "0";
        return String.format("%s%s%s%s", this.description, time, Task.SEP, doneString);
    }

    public abstract String typeString();
}

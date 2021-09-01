package duke.task;

import duke.exceptions.DukeException;

/**
 * The TodoTask class encapsulates a duke.task without any date/time attached to it.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a TodoTask from String.
     * E.g. If passed in ("1 | read book", " \\| ", "1"),
     * this function returns a done TodoTask with description "read book".
     *
     * @param taskString Format should follow <done><delimiter><description>
     * @param delimiter  Delimiter separating the String into its formal parts.
     * @param done       Indicator for a done duke.task.
     * @return TodoTask created from String.
     * @throws DukeException if String is of the wrong format.
     */
    public static Task getTaskFromStorageString(String taskString, String delimiter, String done) throws DukeException {
        try {
            String[] taskSplit = taskString.split(delimiter, 2);
            boolean isDone = taskSplit[0].equals(done);
            return new TodoTask(taskSplit[1], isDone);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    @Override
    public String getTaskFileString(String delimiter, String done, String notDone) {
        return "T" + delimiter + (this.isDone ? done : notDone) + delimiter + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
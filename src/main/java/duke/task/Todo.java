package duke.task;

import java.time.LocalDateTime;

import duke.util.DukeException;

/**
 * Todo is a  task that can be completed without any time constraint.
 */
public class Todo extends Task {
    public Todo(boolean done, String taskName) {
        super(done, taskName);
    }

    /**
     * Turns todo into a string for saving to memory.
     *
     * @return concise string representation of todo.
     */
    @Override
    public String encode() {
        return String.format("T|%s", super.encode());
    }

    /**
     * Turns task into a human readable string form.
     *
     * @return string representation of todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Throws an exception as there is no date time attribute.
     *
     * @param dateTime name to be changed to.
     * @throws DukeException as there is no date time attribute to be set.
     */
    @Override
    public void setDateTime(LocalDateTime dateTime) throws DukeException {
        throw new DukeException("Todos have no date time value");
    }
}

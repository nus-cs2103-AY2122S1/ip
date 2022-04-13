package duke.task;

import java.util.Calendar;

import duke.command.DukeException;

/**
 * This class implements a Todo object which inherits from Task.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Todo extends Task {
    /** Default constructor. */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected void editTime(Calendar cal) throws DukeException {
        throw new DukeException("OOPS!!! TODO does not have deadline or event time to be edited");
    }

    /**
     * Returns the string representation of Todo
     *
     * @return The string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

import java.util.Calendar;

import duke.command.DukeException;

/**
 * This class implements a Task with a description and completion status
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public abstract class Task {
    /** Description of Task. */
    protected String description;

    /** Completion status of Task. */
    protected boolean isDone;

    /** Default constructor. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon for String representation of Task.
     *
     * @return Status icon 'x' for done ' ' for not done
     */
    protected String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    /** Marks task as done. */
    protected void markAsDone() {
        isDone = true;
    }

    protected void editDescription (String newDescription) {
        description = newDescription;
    }

    protected abstract void editTime(Calendar cal) throws DukeException;

    /**
     * Returns the string representation of Task
     *
     * @return The string representation of Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

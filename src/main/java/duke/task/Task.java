package duke.task;

import duke.DukeException;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException(DukeException.Type.DESCRIPTION);
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getCode();
}
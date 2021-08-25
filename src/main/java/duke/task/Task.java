package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    protected String description;
    protected boolean isDone = false;

    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Your description cannot be empty!");
        }
        this.description = description;
    }

    public Task(String description, boolean isDone) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Your description cannot be empty!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String taskToLine() {
        String isDone = this.isDone ? "1" : "0";
        return String.format(" | %s | %s", isDone, this.description);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public LocalDate getDate() {
        return null;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String markDoneIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.markDoneIcon() + " " + this.description;
    }
}

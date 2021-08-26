package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    protected final String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        return new Task(this.taskName, true);
    }

    public String toSaveData() {
        return this.isDone + "|" + this.taskName;
    }

    public boolean hasSameDate(LocalDate date) {
        return false;
    }

    public boolean isBeforeDate(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public String toString() {
        String statusString = this.isDone ? "X" : " ";
        return String.format("[%s] %s", statusString, this.taskName);
    }
}

import java.time.LocalDate;

public class Task {
    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String taskToLine() {
        String isDone = this.isDone ? "1" : "0";
        return String.format(" | %s | %s", isDone, this.description);
    }

    public LocalDate getDate() {
        if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return deadline.timeDue;
        }
        if (this instanceof Event) {
            Event deadline = (Event) this;
            return deadline.timeStart;
        }
        return null;
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

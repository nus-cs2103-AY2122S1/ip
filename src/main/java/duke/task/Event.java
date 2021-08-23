package duke.task;
public class Event extends Task {

    public Event(String desc, String at) {
        super(desc);
        this.dueDate = at;
        this.taskType = "E";
    }

    public Event(String desc, String at, Boolean isDone) {
        super(desc);
        this.dueDate = at;
        this.taskType = "E";
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (at: " + dueDate + ")";
    }
}

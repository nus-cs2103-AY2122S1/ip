package duke.tasks;

public class Deadline extends Task {
    private String marker = "D";
    private String deadline;

    public Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTime() {
        return this.deadline;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString() + " (by:" + deadline + ")";
    }
}

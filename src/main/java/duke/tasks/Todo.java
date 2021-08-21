package duke.tasks;

public class Todo extends Task {
    private String marker = "T";
    private String time = "nil";

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString();
    }
}
package duke;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String getFileString() {
        return String.format("T | %s", super.getFileString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

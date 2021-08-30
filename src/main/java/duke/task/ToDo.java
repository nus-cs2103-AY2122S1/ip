package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public ToDo markAsDone() {
        return new ToDo(super.getName(), true);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

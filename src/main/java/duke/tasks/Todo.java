package duke.tasks;

public class Todo extends Task {
    private String marker = "[T]";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return this.marker + super.toString();
    }
}
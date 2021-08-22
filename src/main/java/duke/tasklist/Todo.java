package duke.tasklist;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String save() {
        return String.format("T | %s", super.save());
    }
}

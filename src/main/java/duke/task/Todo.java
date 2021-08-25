package duke.task;

public class Todo extends Task { //Todo Task to handle todo events

    public Todo(String name) {
        super(name);
    }

    @Override
    public String write() {
        return "T " + super.write();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

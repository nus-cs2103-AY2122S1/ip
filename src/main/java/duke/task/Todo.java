package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getData() {
        return String.format("T,%s", super.getData());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}

package duke;

public class Todo extends Task{
    public Todo(String name, Boolean isDone) {
        super(name, 'T', isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

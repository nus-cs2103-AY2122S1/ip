package duke;

/**
 * class that extends task
 * labelled with an extra [T] when called
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

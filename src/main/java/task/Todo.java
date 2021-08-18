package task;

/**
 * Todo class.
 *
 * This class is the simplest Task which only has a description.
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

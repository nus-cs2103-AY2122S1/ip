/**
 * A task without any deadline.
 *
 * @author limzk126
 * @version Level-4
 */
public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

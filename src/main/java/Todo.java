/**
 * Encapsulates a task that solely has a description
 *
 * @author Clifford
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
/**
 * The To-do class extends Task class and encapsulates a to-do task
 * with no time attached. 
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

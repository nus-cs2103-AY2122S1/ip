/**
 * Represents a specific task (a todo) containing the description of the task
 * that the user wants to add in his or her todo list.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

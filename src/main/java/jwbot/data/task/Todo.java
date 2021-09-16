package jwbot.data.task;

/**
 * Class for todo task
 *
 * @author Yim Jaewon
 */
public class Todo extends Task {

    /**
     * Basic constructor of the class that just uses a super constructor.
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(Todo todo) {
        super(todo.description, todo.isDone);
    }

    /**
     * override toString method for easier printing
     *
     * @return the sting representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

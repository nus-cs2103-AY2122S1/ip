package saber.task;

/**
 * A class to encapsulate a todo type task
 */
public class Todo extends Task {

    /**
     * A constructor for Todo type task
     * @param description the description of the todo task
     * @param isDone the completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * A function to return the string representation of a todo task
     * @return String representation of a todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
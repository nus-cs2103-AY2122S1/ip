import java.util.Scanner;

/**
 * A class to create task with the type of todo.
 *
 * @author Felissa Faustine
 */
public class Todo extends Task {

    /**
     * The constructor for the Todo class.
     *
     * @param taskName The name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Gets the symbol of the todo task.
     *
     * @return The string representation of the symbol of the todo task.
     */
    public String getSymbol() {
        return "[T]";
    }

    /**
     * Gets the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return getSymbol() + super.toString();
    }
}

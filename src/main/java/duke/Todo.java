package duke;

/**
 * Class that inherits from Entry to encapsulate Todos.
 */
public class Todo extends Entry {

    /**
     * Constructor for Todo.
     */
    Todo() {
        super();
    }

    /**
     * Constructor for Todo.
     *
     * @param task The String that will be stored as a Todo.
     */
    Todo(String task) {
        super(task);
    }

    /**
     * Overrides Entry's toString method.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides Entry's saveString method.
     * Returns string to be saved representing the Todo.
     *
     * @return String to be saved representing the Todo.
     */
    @Override
    public String saveString() {
        return "T" + super.saveString();
    }
}

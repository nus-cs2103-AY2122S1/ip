package duke;

public class Todo extends Entry {

    /**
     * Constructor for Todo.
     */
    Todo() {
        super();
    }

    /**
     * Constructor for Todo.
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

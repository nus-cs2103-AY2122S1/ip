public class Todo extends Task{

    /**
     * Constructor for todo class
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * String representation of the todo class
     * @return String. See above.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}

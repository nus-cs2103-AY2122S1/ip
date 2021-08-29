package duke;

/**
 * Represents a subclass of Task that can be added to the List.
 * It is annotated by T in the list.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param name Name of the Task.
     * @throws DukeTodoException If the name is an empty String.
     */
    public Todo(String name) throws DukeTodoException {
        super(name);
        if (name.equals("")) {
            throw new DukeTodoException();
        }
    }

    /**
     * Overloaded Constructor for Todo Class.
     * Used when loading the data file.
     *
     * @param input Array of Strings with type of Task, name, done.
     */
    public Todo(String[] input) {
        super(input[2], input[1].equals("T") ? true : false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString() {
        return "T|" + super.toDataString();
    }

}

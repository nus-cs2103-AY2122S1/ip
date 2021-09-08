package tasks;

/**
 * The Todo class encapsulates all the details of each Todo.
 *
 * @author Quan Teng Foong
 */

public class ToDo extends Task {
    public ToDo(String message) {
        super(message);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this ToDo in storage
     */
    @Override
    public String toStorage() {
        return "T|" + super.toStorage();
    }

    @Override
    public Task clone() {
        return new ToDo(this.getMessage());
    }
}

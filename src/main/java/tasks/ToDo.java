package tasks;

/**
 * The tasks.ToDo class extends Tasks.Task and encapsulates
 */

public class ToDo extends Task {
    public ToDo(String message){
        super(message);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the tasks.ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this tasks.ToDo in storage
     */
    @Override
    public String toStorage() {
        return "T|" + super.toStorage();
    }
}

package skeltal.task.types;

import skeltal.task.Task;

public class ToDo extends Task {

    /**
     * A constructor that initialises a To Do object.
     * @param task The task that should be stored in the object.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * A method that overrides the store() function in the Task parent class,
     * to include the task type of the object.
     *
     * @return A String representation of the object that is readable by the loader.
     */
    @Override
    public String store() {
        return "T | " + super.store();
    }

    /**
     * Returns a String representation of the object, for printing purposes.
     * Eg "[T][ ] Task".
     * @return A string representation of the object for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

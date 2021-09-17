package duke.tasks;

/**
 * Class that initializes the to-do task
 *
 */
public class Todo extends Task {

    /**
     * Constructor for to-do task class
     *
     * @param description String that defines the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of
     * the task data to be written into the storage file
     *
     * @return String representation of data to be written into storage file
     */
    @Override
    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "T | " + i + " | " + this.description;
    }

    /**
     * Returns the string representation of
     * the task data to be outputted
     *
     * @return String representation of data to be outputted
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

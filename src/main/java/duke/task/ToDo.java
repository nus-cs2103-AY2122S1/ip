package duke.task;

/**
 * Represents a ToDo object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class ToDo extends Task {

    /**
     * Constructor for the Todo object.
     * @param description description of the todo task.
     */
    protected ToDo(String description) {
        super(description);
    }

    /**
     * String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToFile() {
        return "T | " + super.saveToFile();
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline || task instanceof Event) {
            return this.GREATER;
        }

        return this.EQUAL;
    }
}

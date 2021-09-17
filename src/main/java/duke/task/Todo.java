package duke.task;

/**
 * Class to encapsulate a Todo
 */
public class Todo extends Task {
    /**
     * Class constructor for Todo Class specifying the description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Class constructor for Todo Class specifying the description and isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return the string format for data source
     *
     * @return           string format of task for data source
     */
    @Override
    public String toData() {
        return "T" + super.toData();
    }
}

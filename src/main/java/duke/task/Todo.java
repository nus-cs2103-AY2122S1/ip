package duke.task;

/**
 * To do class to represent a class without a deadline.
 */
public class Todo extends Task {
    /**
     * Constructor method of To do.
     *
     * @param description Description of a To do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the to do in array format.
     *
     * @return To do in string array format.
     */
    @Override
    public String[] formatTaskInArray() {
        String doneIndicator; 
        if (this.isDone()) {
            doneIndicator = "1";
        } else {
            doneIndicator = "0";
        }
        String[] str;
        str = new String[]{"T", doneIndicator, this.getDescription()};
        return str;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
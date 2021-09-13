package duke.task;

/**
 * Todo class to represent a class without a deadline.
 */
public class Todo extends Task {
    /**
     * Constructor method of Todo.
     *
     * @param description Description of a Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo in array form.
     *
     * @return String array.
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
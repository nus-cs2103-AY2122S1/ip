package kayu.task;

/**
 * Todo class.
 *
 * This class is the simplest {@link kayu.task.Task} which only has a description.
 */
public class Todo extends Task {
    
    public final static String KEYWORD = "T";

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        super(description);
    }

    /**
     * See {@link kayu.task.Task#toEncodedString}.
     */
    @Override
    public String toEncodedString() {
        return KEYWORD + SPLIT_TEMPLATE + super.toEncodedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

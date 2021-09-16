package chad.task;

/**
 * Represents a to-do task.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ToDoTask extends Task {

    private static final String TYPE_MARK = "T";
    private static final String FILE_REPRESENTATION_TEMPLATE = "%s %d %s";
    private static final String TYPE_INDICATOR_TEMPLATE = "[%s]";
    private static final int DONE_FILE_REPRESENTATION = 1;
    private static final int NOT_DONE_FILE_REPRESENTATION = 0;

    /**
     * Creates a to-do task with the given task description.
     *
     * @param taskDescription The description of the to-do task.
     */
    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getFileRepresentation() {
        int done = isDone() ? DONE_FILE_REPRESENTATION : NOT_DONE_FILE_REPRESENTATION;
        String description = getDescription();
        return String.format(FILE_REPRESENTATION_TEMPLATE, TYPE_MARK, done, description);
    }

    @Override
    String getTypeIndicator() {
        return String.format(TYPE_INDICATOR_TEMPLATE, TYPE_MARK);
    }
}

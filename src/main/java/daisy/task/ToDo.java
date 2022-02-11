package daisy.task;

/**
 * ToDo class that handles todo tasks.
 */
public class ToDo extends Task {

    static final String TODO_STRING_FORMAT = "[T]%s %s";
    static final String TODO_FILE_FORMAT = "T // %s // %s";
    /**
     * Constructs the ToDo object.
     *
     * @param description Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format(TODO_STRING_FORMAT, super.toString(), getTagsString());
    }

    @Override
    public String convertToFileFormat() {
        return String.format(TODO_FILE_FORMAT, super.convertToFileFormat(), getTagsFileFormat());
    }
}

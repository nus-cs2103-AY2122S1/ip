package duke.task;

/**
 * Represents a To-do.
 */
public class ToDo extends Task {
    public static final char TEXT_ENCODING_CHAR = 'T';

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toText() {
        String[] props = {String.valueOf(TEXT_ENCODING_CHAR), super.getStatusIcon(), super.getName()};
        return String.join(FIELD_SEPARATOR, props);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TEXT_ENCODING_CHAR, super.toString());
    }
}

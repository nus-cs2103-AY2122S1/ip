package duke.domain;

/**
 * Encapsulates a regular task.
 */
public class Todo extends Task {
    public static final String TYPE_STRING = "T";

    /**
     * Creates a to-do task with the given name.
     * @param name Name of task.
     */
    public Todo(String name) {
        super(name);
        // typeString = TYPE_STRING;
    }

    /**
     * Creates a to-do task with the given name and completion status.
     * @param name Name of task.
     * @param isDone Whether task is to be marked as complete upon creation.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
        // typeString = TYPE_STRING;
    }

    /**
     * Returns a To-do object from a given array of fields. Effectively the inverse of storageFields.
     * @return A To-do object.
     */
    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        return new Todo(name, isDone);
    }
}

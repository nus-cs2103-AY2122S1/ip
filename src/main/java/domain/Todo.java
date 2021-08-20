package domain;

/**
 * Encapsulates a regular task.
 */
public class Todo extends Task {
    public static final String TYPE_STRING = "T";

    public Todo(String name) {
        super(name);
        // typeString = TYPE_STRING;
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
        // typeString = TYPE_STRING;
    }

    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        return new Todo(name, isDone);
    }
}

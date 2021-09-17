package duke;

/**
 * Represents a Task Class.
 * Handles operations that are common for all Tasks.
 */
public class Task {
    public static final String STORAGE_SEPARATOR = "|";
    private String name;
    private boolean isDone;

    /**
     * Constructor for Task Class.
     * The done status is false.
     *
     * @param name Name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for Task Class when loading data.
     *
     * @param name Name of the Task.
     * @param done Done status of the Task.
     */
    public Task(String name, Boolean done) {
        this.name = name;
        this.isDone = done;
    }

    /**
     * Returns the name of the Task.
     *
     * @return Name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Handles the creation of new Tasks when loading the data from file.
     *
     * @param input The line from data.
     * @return A Task based on the input.
     */
    public static Task create(String[] input) {
        if (input[0].equals("T")) {
            return new Todo(input);
        } else if (input[0].equals("E")) {
            return new Event(input);
        } else if (input[0].equals("D")) {
            return new Deadline(input);
        }
        return new Task("ERROR");
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String toDataString() {
        return (this.isDone ? "T" : "F") + Task.STORAGE_SEPARATOR + this.name;
    }
}

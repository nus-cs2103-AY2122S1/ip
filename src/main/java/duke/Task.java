package duke;

/**
 * A representation of a Task Class.
 * Handles operations that are common for all Tasks.
 */
public class Task {
    public static final String STORAGE_SEPARATOR = "|";
    private String name;
    private boolean done;

    /**
     * Constructor for Task Class.
     * The done status is false.
     *
     * @param name Name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Overloaded constructor for Task Class.
     * Used when loading data.
     *
     * @param name Name of the Task.
     * @param done Done status of the Task.
     */
    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
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
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Handles the creation of new Tasks.
     * Used when loading the data from file.
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
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

    public String toDataString() {
        return (this.done ? "T" : "F") + Task.STORAGE_SEPARATOR + this.name;
    }
}

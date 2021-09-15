package duke.tasks;

/**
 * The {@code TodoTask} class extends from {@code Task}.
 */
public class TodoTask extends Task {
    /**
     * Initializes a {@code TodoTask} with {@code String description}.
     *
     * @param description Description of {@code Task}.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Initializes a {@code TodoTask} with {@code String description}
     * and {@code boolean isDone status}.
     *
     * @param description Description of {@code Task}.
     * @param isDone Status of {@code Task}.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Checks if given {@code TaskTypes} query matches this {@code Task}.
     *
     * @param query {@code TaskTypes} to be compared against this {@code Task}
     * @return {@code boolean} value representing a match
     */
    public boolean isMatch(TaskTypes query) {
        return query.equals(TaskTypes.TODO);
    }

    @Override
    public String getType() {
        return "[T]";
    }

    /**
     * Prints {@code TodoTask} description with the prefix [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

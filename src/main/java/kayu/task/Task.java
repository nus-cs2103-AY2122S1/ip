package kayu.task;

/**
 * Represents the abstract class that acts as a base for all tasks handled by {@link kayu.Kayu}.
 */
public abstract class Task {

    /** Template used for encoding storage. */
    public static final String SPLIT_TEMPLATE = " # ";

    /** Done state for encoding/decoding. */
    public static final String DONE = "1";

    /** Not done state for encoding/decoding. */
    public static final String NOT_DONE = "0";

    private final String description;
    private boolean isDone;

    /**
     * Initializes a new Task instance.
     *
     * @param description String description of Task.
     * @param isDone Boolean true if complete, else false.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Initializes a new Task instance. Overloaded constructor that sets
     * {@link #isDone} field to false.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task instance.
     *
     * @return Description of the Task as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion state of the Task.
     *
     * @return Boolean isDone, true if complete else false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the completion state of the Task instance.
     *
     * @param isDone New boolean state.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /** Sets isDone field to true. */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Generates a String icon whether Task class is done or not.
     * '[X]' represents done while '[ ]' represents not done.
     *
     * @return String icon based on done status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Provides the encoded string representation of this Task instance.
     *
     * @return Encoded String representation of instance.
     */
    public String toEncodedString() {
        return ((isDone) ? DONE : NOT_DONE) + SPLIT_TEMPLATE + description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getStatusIcon() + ' ' + description;
    }
}

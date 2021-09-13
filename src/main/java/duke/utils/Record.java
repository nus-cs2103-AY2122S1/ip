package duke.utils;

public class Record {
    private final String message;
    private final boolean isTerminated;

    /**
     * Creates a new record.
     *
     * @param message Message Duke returns.
     * @param isTerminated Is Duke supposed to terminate after this?
     */
    public Record(String message, boolean isTerminated) {
        this.message = message;
        this.isTerminated = isTerminated;
    }

    /**
     * Creates a non-terminating record.
     *
     * @param message Message Duke returns.
     */
    public Record(String message) {
        this(message, false);
    }

    /**
     * Getter for message.
     *
     * @return message.
     */
    public String msg() {
        return message;
    }

    /**
     * Getter for terminate.
     *
     * @return Is Duke supposed to terminate after this?
     */
    public boolean bye() {
        return isTerminated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Record) {
            Record that = (Record) obj;
            return this.message.equals(that.message) && this.isTerminated == that.isTerminated;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return message;
    }
}

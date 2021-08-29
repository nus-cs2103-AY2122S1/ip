package duke.utils;

public class Record {
    private final String message;
    private final boolean terminate;

    /**
     * Creates a new record.
     *
     * @param message
     * @param terminate
     */
    public Record(String message, boolean terminate) {
        this.message = message;
        this.terminate = terminate;
    }

    /**
     * Creates a non-terminating record.
     *
     * @param message
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
     * @return
     */
    public boolean bye() {
        return terminate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Record) {
            Record that = (Record) obj;
            return this.message.equals(that.message) && this.terminate == that.terminate;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return message;
    }
}

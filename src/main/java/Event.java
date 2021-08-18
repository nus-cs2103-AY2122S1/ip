public class Event {
    private String command;
    private String message;

    /**
     * Constructor of the class `Event`.
     */
    public Event(String command) {
        this.command = command;
    }

    /**
     * Reads and processes a command, updates the message to be printed.
     */
    public void process() {
        this.message = this.command;
    }

    /**
     * Returns the event's processed result as a string.
     *
     * @return String representation of the result of processing an event.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                this.message + "\n" +
                "____________________________________________________________\n";
    }
}

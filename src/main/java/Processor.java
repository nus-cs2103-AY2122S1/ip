/**
 * Represents a processor that can process a command and print out the results.
 */
public class Processor {
    /** The command to be processed */
    protected String command;
    /** Message generated */
    protected String message;
    /** Task involved in the command */
    protected Task task;

    /**
     * Constructor of the class `Processor`.
     *
     * @param command The command received.
     */
    public Processor(String command) {
        this.command = command;
    }

    /**
     * Updates the message to be printed.
     */
    public void process() {
        this.message = this.command;
    }

    /**
     * Returns the command's processed result as a string.
     *
     * @return String representation of the result of processing a command.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                this.message +
                "____________________________________________________________\n";
    }
}

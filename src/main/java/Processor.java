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
    /** Duke program to be processed */
    protected Duke duke;

    /**
     * Constructor of the class `Processor`.
     *
     * @param command The command received.
     */
    public Processor(String command, Duke duke) {
        this.command = command;
        this.duke = duke;
    }

    /**
     * Updates the message to be printed.
     *
     * @return Whether the program is still running.
     */
    public boolean process() {
        this.message = this.command;
        return true;
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

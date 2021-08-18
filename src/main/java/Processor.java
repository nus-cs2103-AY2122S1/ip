public class Processor {
    protected String command;
    protected String message;
    protected Task task;

    /**
     * Constructor of the class `Processor`.
     *
     * @param command The command received.
     */
    public Processor(String command) {
        this.command = command;
        this.task = new Task(command);
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

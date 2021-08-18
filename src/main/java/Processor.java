public class Processor {
    private String command;
    private String message;

    /**
     * Constructor of the class `Processor`.
     */
    public Processor(String command) {
        this.command = command;
    }

    /**
     * Reads and processes a command, updates the message to be printed.
     * Prints the result of processing.
     */
    public void process() {
        this.message = this.command;
        System.out.println(this);
    }

    /**
     * Returns the command's processed result as a string.
     *
     * @return String representation of the result of processing a command.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                this.message + "\n" +
                "____________________________________________________________\n";
    }
}

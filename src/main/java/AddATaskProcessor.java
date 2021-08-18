public class AddATaskProcessor extends Processor {
    /**
     * Constructor of the class `AddATaskProcessor`.
     *
     * @param command The command received.
     */
    public AddATaskProcessor(String command) {
        super(command);
    }

    /**
     * Updates the message to be printed.
     */
    @Override
    public void process() {
        Duke.updateList(this.command);
        this.message = "added: " + this.command;
    }
}

public class AddATaskProcessor extends Processor {
    /**
     * Constructor of the class `AddATaskProcessor`.
     *
     * @param command The command received.
     */
    public AddATaskProcessor(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Updates the message to be printed.
     */
    @Override
    public void process() {
        Duke.updateList(this.task);
        this.message = String.format(
                "Got it. I've added this task:\n  %s\n" +
                        "Now you have %o tasks in the list.\n", this.task, Duke.getNumOfTasks());
    }
}

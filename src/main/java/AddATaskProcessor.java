public class AddATaskProcessor extends Processor {
    /**
     * Constructor of the class `AddATaskProcessor`.
     *
     * @param task Task added.
     */
    public AddATaskProcessor(Task task) {
        super("add");
        this.task = task;
    }

    /**
     * Updates the message to be printed.
     */
    @Override
    public void process() {
        Duke.addToList(this.task);
        this.message = String.format(
                "Got it. I've added this task:\n  %s\n" +
                        "Now you have %o tasks in the list.\n", this.task, Duke.getNumOfTasks());
    }
}

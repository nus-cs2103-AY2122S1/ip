public class TaskDoneProcessor extends Processor {
    /**
     * Constructor of the class `TaskDoneProcessor`.
     *
     * @param task The finished task.
     */
    public TaskDoneProcessor(Task task) {
        super("done");
        this.task = task;
        this.message = "Nice! I've marked this task as done:\n";
    }

    /**
     * Generates the message to be printed.
     */
    @Override
    public void process() {
        this.task.markAsDone();
        this.message += String.format("  %s\n", this.task.toString());
    }
}

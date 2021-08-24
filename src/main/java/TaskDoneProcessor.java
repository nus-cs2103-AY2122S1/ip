/**
 * Represents a processor that can mark a task as done. A subclass of the Processor class.
 */
public class TaskDoneProcessor extends Processor {
    /**
     * Constructor of the class `TaskDoneProcessor`.
     */
    public TaskDoneProcessor(Task task, Duke duke) {
        super("done", duke);
        this.task = task;
        this.message = "Nice! I've marked this task as done:\n";
    }

    /**
     * Generates the message to be printed.
     *
     * @return Whether the program is still running.
     */
    @Override
    public boolean process() {
        this.task.markAsDone();
        this.duke.rewriteFile();
        this.message += String.format("  %s\n", this.task.toString());
        return true;
    }
}

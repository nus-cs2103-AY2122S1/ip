public abstract class Task {
    /** The description of the task */
    protected String description;

    /** The status of the task */
    protected boolean isDone;

    /** Task constructor */
    private Task() {
        this("");
    }
    
    /**
     * Task constructor.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Task constructor.
     *
     * @param description the description of the task
     * @param isDone the status of the task
     */
    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks the task's status as done */
    public void markTaskAsDone() {
        isDone = true;
        Printer.print("Nice! I've marked this task as done:", this.toString());
    }

    /** Prints to screen for every instantiation of a Task. */
    protected static void feedback(String output) {
        Printer.print(
                "Got it. I've added this task:",
                String.format("\t%s", output),
                String.format(
                        "Now you have %d %s in the list",
                        Memory.getSize(),
                        Memory.getSize() == 1 ? "task" : "tasks"));
    }
}

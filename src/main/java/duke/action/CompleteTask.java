package duke.action;

import duke.Response;
import duke.task.Task;

/**
 * duke.action.CompleteTask is an Action that marks a duke.task.Task as done.
 */
public class CompleteTask implements Action {
    private final Task task;

    /**
     * Creates an Action duke.action.CompleteTask that marks a duke.task.Task as done.
     * @param task The duke.task.Task to be marked as done.
     */
    public CompleteTask(Task task) {
        assert task != null : "Task to add should not be null";

        this.task = task;
    }

    /**
     * Marks the duke.task.Task as done.
     * @return The duke.Response.
     */
    @Override
    public Response execute() {
        this.task.markDone();
        return this.respond();
    }

    /**
     * Returns the duke.Response for marking the duke.task.Task as done.
     * @return The duke.Response.
     */
    private Response respond() {
        return new Response(new String[]{
            "Nice! I've marked this task as done:",
            String.format("  %s", this.task.toString()),
        });
    }
}

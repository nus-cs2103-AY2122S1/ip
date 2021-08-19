/**
 * CompleteTask is an Action that marks a Task as done.
 */
public class CompleteTask implements Action {
    private final Task task;

    /**
     * Creates an Action CompleteTask that marks a Task as done.
     * @param task The Task to be marked as done.
     */
    public CompleteTask(Task task) {
        this.task = task;
    }

    /**
     * Marks the Task as done.
     * @return The Response.
     */
    @Override
    public Response execute() {
        this.task.isDone = true;
        return this.respond();
    }

    /**
     * Returns the Response for marking the Task as done.
     * @return The Response.
     */
    private Response respond() {
        return new Response(new String[]{
                "Nice! I've marked this task as done:",
                String.format("  %s", this.task.toString()),
        });
    }
}

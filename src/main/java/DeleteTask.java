/**
 * DeleteTask is an Action that deletes a Task from a TaskCollection.
 */
public class DeleteTask implements Action {
    private final int taskId;
    private final TaskCollection taskCollection;

    /**
     * Creates an Action DeleteTask that deletes a Task from a TaskCollection.
     * @param taskId The identifier of the Task to be deleted.
     * @param taskCollection The TaskCollection to delete the Task from.
     */
    public DeleteTask(int taskId, TaskCollection taskCollection) {
        this.taskId = taskId;
        this.taskCollection = taskCollection;
    }

    /**
     * Deletes the Task from the TaskCollection.
     * @return The Response.
     */
    @Override
    public Response execute() {
        Task deletedTask = this.taskCollection.delete(this.taskId);
        return this.respond(deletedTask);
    }

    /**
     * Returns the Response for successfully adding the Task to the TaskCollection.
     * @param deletedTask The Task that was deleted.
     * @return The Response.
     */
    private Response respond(Task deletedTask) {
        return new Response(new String[]{
                "Noted. I've removed this task:",
                String.format("  %s", deletedTask.toString()),
                String.format("Now you have %d tasks in the list.", this.taskCollection.size())
        });
    }
}

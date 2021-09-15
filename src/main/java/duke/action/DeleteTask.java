package duke.action;

import duke.Response;
import duke.task.Task;
import duke.task.TaskCollection;

/**
 * duke.action.DeleteTask is an Action that deletes a duke.task.Task from a duke.task.TaskCollection.
 */
public class DeleteTask implements Action {
    private final int taskId;
    private final TaskCollection taskCollection;

    /**
     * Creates an Action duke.action.DeleteTask that deletes a duke.task.Task from a duke.task.TaskCollection.
     * @param taskId The identifier of the duke.task.Task to be deleted.
     * @param taskCollection The duke.task.TaskCollection to delete the duke.task.Task from.
     */
    public DeleteTask(int taskId, TaskCollection taskCollection) {
        assert taskId > 0 : "ID of task to be deleted should be greater than 0";
        assert taskCollection != null : "Task collection to add task to should exist";

        this.taskId = taskId;
        this.taskCollection = taskCollection;
    }

    /**
     * Deletes the duke.task.Task from the duke.task.TaskCollection.
     * @return The duke.Response.
     */
    @Override
    public Response execute() {
        Task deletedTask = this.taskCollection.delete(this.taskId);
        return this.respond(deletedTask);
    }

    /**
     * Returns the duke.Response for successfully adding the duke.task.Task to the duke.task.TaskCollection.
     * @param deletedTask The duke.task.Task that was deleted.
     * @return The duke.Response.
     */
    private Response respond(Task deletedTask) {
        return new Response(new String[]{
            "Noted. I've removed this task:",
            String.format("  %s", deletedTask.toString()),
            String.format("Now you have %d tasks in the list.", this.taskCollection.size())
        });
    }
}

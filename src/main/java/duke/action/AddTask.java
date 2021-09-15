package duke.action;

import duke.Response;
import duke.task.Task;
import duke.task.TaskCollection;

/**
 * duke.action.AddTask is an Action that adds a duke.task.Task to a duke.task.TaskCollection.
 */
public class AddTask implements Action {
    private final Task task;
    private final TaskCollection taskCollection;

    /**
     * Creates an Action duke.action.AddTask that adds a duke.task.Task into a duke.task.TaskCollection.
     * @param task The duke.task.Task to be added.
     * @param taskCollection The duke.task.TaskCollection to add the duke.task.Task into.
     */
    public AddTask(Task task, TaskCollection taskCollection) {
        assert task != null : "Task to add should not be null";
        assert taskCollection != null : "Task collection to add task to should exist";

        this.task = task;
        this.taskCollection = taskCollection;
    }

    /**
     * Adds the duke.task.Task into the duke.task.TaskCollection.
     * @return The duke.Response.
     */
    @Override
    public Response execute() {
        this.taskCollection.add(this.task);
        return this.respond();
    }

    /**
     * Returns the duke.Response for successfully adding the duke.task.Task to the duke.task.TaskCollection.
     * @return The duke.Response.
     */
    private Response respond() {
        return new Response(new String[]{
            "Got it. I've added this task:",
            String.format("  %s", this.task.toString()),
            String.format("Now you have %d tasks in the list.", this.taskCollection.size())
        });
    }
}

package duke.action;

import duke.Response;
import duke.task.Task;
import duke.task.TaskCollection;

/**
 * AddTask is an Action that adds a Task to a TaskCollection.
 */
public class AddTask implements Action {
    private final Task task;
    private final TaskCollection taskCollection;

    /**
     * Creates an Action AddTask that adds a Task into a TaskCollection.
     * @param task The Task to be added.
     * @param taskCollection The TaskCollection to add the Task into.
     */
    public AddTask(Task task, TaskCollection taskCollection) {
        assert task != null : "Task to add should not be null";
        assert taskCollection != null : "Task collection to add task to should exist";

        this.task = task;
        this.taskCollection = taskCollection;
    }

    /**
     * Adds the Task into the TaskCollection.
     * @return The duke.Response.
     */
    @Override
    public Response execute() {
        this.taskCollection.add(this.task);
        return this.respond();
    }

    /**
     * Returns the Response for successfully adding the Task to the TaskCollection.
     * @return The Response.
     */
    private Response respond() {
        return new Response(new String[]{
            "Got it. I've added this task:",
            String.format("  %s", this.task.toString()),
            String.format("Now you have %d tasks in the list.", this.taskCollection.size())
        });
    }
}

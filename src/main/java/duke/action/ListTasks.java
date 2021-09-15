package duke.action;

import duke.Response;
import duke.task.TaskCollection;

/**
 * duke.action.ListTasks is an Action that lists the Tasks in a duke.task.TaskCollection.
 */
public class ListTasks implements Action {
    private final TaskCollection taskCollection;

    /**
     * Creates an Action duke.action.ListTasks that lists the Tasks in a duke.task.TaskCollection.
     * @param taskCollection The duke.task.TaskCollection.
     */
    public ListTasks(TaskCollection taskCollection) {
        assert taskCollection != null : "Task collection to add task to should exist";

        this.taskCollection = taskCollection;
    }

    /**
     * Lists all the Tasks in the duke.task.TaskCollection.
     * @return The duke.Response.
     */
    @Override
    public Response execute() {
        return this.respond();
    }

    /**
     * Returns the duke.Response for listing all the Tasks in the duke.task.TaskCollection.
     * @return The duke.Response.
     */
    private Response respond() {
        return new Response(new String[]{
            "Here are the tasks in your list:",
            this.taskCollection.toString(),
        });
    }
}

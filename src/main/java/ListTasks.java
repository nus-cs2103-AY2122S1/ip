/**
 * ListTasks is an Action that lists the Tasks in a TaskCollection.
 */
public class ListTasks implements Action {
    private final TaskCollection taskCollection;

    /**
     * Creates an Action ListTasks that lists the Tasks in a TaskCollection.
     * @param taskCollection The TaskCollection.
     */
    public ListTasks(TaskCollection taskCollection) {
        this.taskCollection = taskCollection;
    }

    /**
     * Lists all the Tasks in the TaskCollection.
     * @return The Response.
     */
    @Override
    public Response execute() {
        return this.respond();
    }

    /**
     * Returns the Response for listing all the Tasks in the TaskCollection.
     * @return The Response.
     */
    private Response respond() {
        return new Response(new String[]{
                "Here are the tasks in your list:",
                this.taskCollection.toString(),
        });
    }
}

package duke.action;

import java.util.List;

import duke.Response;
import duke.task.Task;
import duke.task.TaskCollection;

/**
 * FindTasks is an Action that finds a List of Tasks that match a given String.
 */
public class FindTasks implements Action {
    private final String string;
    private final TaskCollection taskCollection;

    /**
     * Creates an Action DeleteTask that finds a List of Tasks that match a given String.
     * @param string The String to match.
     * @param taskCollection The TaskCollection to find the Tasks from.
     */
    public FindTasks(String string, TaskCollection taskCollection) {
        this.string = string;
        this.taskCollection = taskCollection;
    }

    /**
     * Finds the Tasks from the TaskCollection.
     * @return The duke.Response
     */
    public Response execute() {
        List<Task> tasks = this.taskCollection.findTasks(this.string);
        return this.toResponse(tasks);
    }

    /**
     * Creates the FindTasks Response from the taskList provided.
     * @param taskList The List of Tasks
     * @return The Response
     */
    private Response toResponse(List<Task> taskList) {
        String[] responseMessages = new String[taskList.size() + 1];
        responseMessages[0] = "Here are the matching tasks in your list:";
        for (int index = 0; index < taskList.size(); index++) {
            int taskIdentifier = index + 1;
            responseMessages[index + 1] = String.format("%d.%s", taskIdentifier, taskList.get(index).toString());
        }
        return new Response(responseMessages);
    }
}

package blue.handler;

import java.util.List;

import blue.BlueException;
import blue.TaskList;
import blue.task.Deadline;
import blue.task.Event;
import blue.task.Task;
import blue.task.ToDo;

/**
 * Shows the number of tasks that have been completed, for each type of task.
 */
public class StatsHandler extends CommandHandler {
    private static final String RESULT_START = "Here are the stats:\n";

    public StatsHandler(TaskList taskList) {
        super(taskList);
    }

    /**
     * Handles the user input.
     *
     * @param input User input.
     * @return Response.
     */
    @Override
    public String handle(String input) throws BlueException {
        List<Task> allTasks = taskList.getAll();
        long todoCount = allTasks.stream().filter((task) -> (task instanceof ToDo)).count();
        long deadlineCount = allTasks.stream().filter((task) -> (task instanceof Deadline)).count();
        long eventCount = allTasks.stream().filter((task) -> (task instanceof Event)).count();
        String response = RESULT_START;
        response += "ToDo: " + todoCount + "\n";
        response += "Deadline: " + deadlineCount + "\n";
        response += "Event: " + eventCount + "\n";
        return response;
    }
}

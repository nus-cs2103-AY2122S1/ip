package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

/**
 * Represents a list command which
 * lists all current tasks in the database.
 */
public class ListCommand extends Command {

    /**
     * Lists all current tasks.
     */
    @Override
    public String run() {
        StringBuilder response = new StringBuilder(
                Message.COMMAND_LIST.getMessage());
        if (TaskList.size() == 0) {
            response.append("\t Hurray there is no pending task!\n");
            return response.toString();
        }
        for (int i = 0; i < TaskList.size(); ++i) {
            int index = i + 1;
            Task task = TaskList.get(i);
            response.append("\t ")
                    .append(index).append(". ")
                    .append(task).append("\n");
        }
        return response.toString();
    }
}

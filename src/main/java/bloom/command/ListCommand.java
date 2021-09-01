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
    public void run() {
        System.out.println(Message.COMMAND_LIST.getMessage());
        for (int i = 0; i < TaskList.size(); ++i) {
            int index = i + 1;
            Task task = TaskList.get(i);
            System.out.println("\t " + index + ". " + task);
        }
    }
}

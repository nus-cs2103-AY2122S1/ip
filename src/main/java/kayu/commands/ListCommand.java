package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

import java.util.List;

/**
 * ListCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that uses the keyword {@link #COMMAND_WORD}. 
 * It returns the {@link kayu.task.Task} that are present in {@link kayu.service.TaskList}.
 */
public class ListCommand extends Command {
        
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * See {@link kayu.commands.Command#execute(TaskList)}.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return CommandMessage.MESSAGE_EMPTY_LIST;
        }
        StringBuilder tasksAsString = new StringBuilder(CommandMessage.MESSAGE_LIST_CONTENTS);
        for (int idx = 0; idx < tasks.size(); idx ++) {
            tasksAsString.append(String.format("\n\t%d. %s", idx + 1, tasks.get(idx)));
        }
        return tasksAsString.toString();
    }
}

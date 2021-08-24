package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

import java.util.List;

public class ListCommand extends Command {
        
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super(CommandType.LIST);
    }
    
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

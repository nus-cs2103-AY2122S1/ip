package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_EMPTY_LIST;
import static kayu.commands.CommandMessage.MESSAGE_LIST_CONTENTS;
import static kayu.commands.CommandType.LIST;
        
import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

import java.util.List;

public class ListCommand extends Command {
        
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super(LIST);
    }
    
    @Override
    public String execute(TaskList taskList) throws DukeException {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }
        
        StringBuilder tasksAsString = new StringBuilder(MESSAGE_LIST_CONTENTS);
        for (int idx = 0; idx < tasks.size(); idx ++) {
            tasksAsString.append(String.format("\n\t%d. %s", idx + 1, tasks.get(idx)));
        }
        
        return tasksAsString.toString();
    }
}

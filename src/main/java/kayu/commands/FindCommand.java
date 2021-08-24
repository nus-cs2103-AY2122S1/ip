package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.MESSAGE_MATCHING_CONTENTS;
import static kayu.commands.CommandMessage.MESSAGE_NO_MATCHING_CONTENTS;
import static kayu.commands.CommandType.FIND;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

import java.util.Map;

/**
 * FindCommand class.
 * 
 * This class is an instance of {@link kayu.commands.Command} that uses the 
 * keyword {@link #COMMAND_WORD}. It is used to help the user find the relevant 
 * {@link kayu.task.Task} whose description matches with the keyword parameters specified.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public FindCommand(String commandParams) {
        super(FIND, commandParams);
    }

    /**
     * See {@link kayu.commands.Command#execute(TaskList)}.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String keyword = commandParams.trim();
        if (keyword.isEmpty()) {
            throw new DukeException(String.format(ERROR_EMPTY_PARAMS, COMMAND_WORD));
        }

        Map<Integer, Task> taskMap = taskList.findMatchingTasks(keyword);
        if (taskMap.isEmpty()) {
            return String.format(MESSAGE_NO_MATCHING_CONTENTS, keyword);
        }
        
        String header = String.format(MESSAGE_MATCHING_CONTENTS, keyword);
        StringBuilder resultTasksAsString = new StringBuilder(header);
        taskMap.forEach((number, task) -> {
            String line = String.format("\n\t%d. %s", number + 1, task);
            resultTasksAsString.append(line);
        });
        
        return resultTasksAsString.toString();
    }
}

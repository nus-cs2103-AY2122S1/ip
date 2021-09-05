package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.MESSAGE_MATCHING_CONTENTS;
import static kayu.commands.CommandMessage.MESSAGE_NO_MATCHING_CONTENTS;

import java.util.Map;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Represents a {@link kayu.commands.Command} that finds the relevant {@link kayu.task.Task}s
 * whose description matches with the keyword specified.
 */
public class FindCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "find";

    /**
     * Initializes a Find- {@link kayu.commands.Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public FindCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        String[] keywords = extractKeywords();
        String formattedParameters = generateFormattedParameters(keywords); // for message
        Map<Integer, Task> taskMap = taskList.findTasksByKeywords(keywords);
        
        return generateResponse(formattedParameters, taskMap);
    }
    
    private String[] extractKeywords() throws KayuException {
        String keyword = commandParams.trim();
        if (keyword.isEmpty()) {
            throw new KayuException(String.format(ERROR_EMPTY_PARAMS, COMMAND_WORD));
        }
        return keyword.split(" ");
    }

    private String generateFormattedParameters(String... keywords) {
        StringBuilder parameters = new StringBuilder();
        for (int idx = 0; idx < keywords.length; idx++) {
            parameters.append(keywords[idx]);
            if (idx != keywords.length - 1) {
                parameters.append(", ");
            }
        }
        return parameters.toString();
    }
    
    private String generateFormattedTaskListResponse(Map<Integer, Task> taskMap) {
        StringBuilder resultTasksAsString = new StringBuilder();
        taskMap.forEach((number, task) -> {
            String line = String.format("\n%d. %s", number + 1, task);
            resultTasksAsString.append(line);
        });
        return resultTasksAsString.toString();
    }
    
    private String generateResponse(String params, Map<Integer, Task> taskMap) {
        if (taskMap.isEmpty()) {
            return String.format(MESSAGE_NO_MATCHING_CONTENTS, params);
        }
        
        String header = String.format(MESSAGE_MATCHING_CONTENTS, params);
        String body = generateFormattedTaskListResponse(taskMap);
        return header.concat(body);
    }
}

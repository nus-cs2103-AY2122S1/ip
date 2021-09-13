package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.MESSAGE_ITEM_FORMAT;
import static kayu.commands.CommandMessage.MESSAGE_MATCHING_CONTENTS;
import static kayu.commands.CommandMessage.MESSAGE_NO_MATCHING_CONTENTS;

import java.util.Map;
import java.util.stream.Collectors;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
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
    public String execute(TaskList taskList,
                          TaskStorage taskStorage,
                          NoteList noteList,
                          NoteStorage noteStorage)
            throws KayuException, StorageException {

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

    // generate parameter string '{}, {}, {} ...'
    private String generateFormattedParameters(String... keywords) {
        return String.join(", ", keywords);
    }

    private String generateResponse(String params, Map<Integer, Task> taskMap) {
        if (taskMap.isEmpty()) {
            return String.format(MESSAGE_NO_MATCHING_CONTENTS, params);
        }

        String header = String.format(MESSAGE_MATCHING_CONTENTS, params);
        String body = generateFormattedTaskListResponse(taskMap);
        return header.concat(body);
    }

    private String generateFormattedTaskListResponse(Map<Integer, Task> taskMap) {
        return taskMap.entrySet()
                .stream()
                .map(this::convertToTaskString)
                .collect(Collectors.joining("\n"));
    }

    private String convertToTaskString(Map.Entry<Integer, Task> entry) {
        int number = entry.getKey() + 1; // for 1-indexing
        String taskAsString = entry.getValue().toString();
        return String.format(MESSAGE_ITEM_FORMAT, number, taskAsString);
    }
}

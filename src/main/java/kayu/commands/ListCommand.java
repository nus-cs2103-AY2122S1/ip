package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_EMPTY_LIST;
import static kayu.commands.CommandMessage.MESSAGE_LIST_CONTENTS;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Represents a {@link kayu.commands.Command} that provides the {@link kayu.task.Task}
 * that are present in {@link kayu.service.TaskList}.
 */
public class ListCommand extends Command {
    
    /** Keyword for command. */
    public static final String COMMAND_WORD = "list";

    /**
     * Initializes a List- {@link kayu.commands.Command}.
     */
    public ListCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return MESSAGE_EMPTY_LIST;
        }
        return generateFormattedTaskListResponse(tasks);
    }
    
    private String generateFormattedTaskListResponse(List<Task> tasks) {
        StringBuilder tasksAsString = new StringBuilder(MESSAGE_LIST_CONTENTS);
        
        String stringedTasks = IntStream.range(0, tasks.size())
                .boxed()
                .map(idx -> String.format("%d. %s", idx + 1, tasks.get(idx)))
                .collect(Collectors.joining("\n"));
        
        tasksAsString.append(stringedTasks);
        return tasksAsString.toString();
    }
}

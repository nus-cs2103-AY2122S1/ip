package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_EMPTY_TASK_LIST;

import java.util.List;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Task;

/**
 * Represents a {@link kayu.commands.Command} that provides the {@link kayu.task.Task}
 * that are present in {@link TaskList}.
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
    public String execute(TaskList taskList,
                          TaskStorage taskStorage,
                          NoteList noteList,
                          NoteStorage noteStorage)
            throws KayuException, StorageException {

        return listTasks(taskList);
    }

    private String listTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return MESSAGE_EMPTY_TASK_LIST;
        }
        return CommandUtils.generateFormattedItemListResponse(tasks);
    }
}

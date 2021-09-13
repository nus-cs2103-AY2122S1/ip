package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_NULL_TASK;
import static kayu.commands.CommandMessage.MESSAGE_CREATED_TODO;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Task;
import kayu.task.Todo;

/**
 * Represents an {@link Command} that creates a {@link kayu.task.Todo}
 * and saves it in {@link TaskList}.
 */
public class TodoCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "todo";

    /**
     * Initializes a Todo- {@link Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public TodoCommand(String commandParams) {
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

        Task todo = createTask();
        updateTasks(taskList, taskStorage, todo);

        return String.format(MESSAGE_CREATED_TODO, todo, taskList.getCurrentCapacity());
    }

    private Task createTask() throws KayuException {
        String desc = extractDesc(commandParams);
        return new Todo(desc);
    }

    // call on wrapper method in util class
    private String extractDesc(String params) throws KayuException {
        return CommandUtils.extractDesc(new String[] {params}, COMMAND_WORD);
    }

    private void updateTasks(TaskList taskList, TaskStorage taskStorage, Task task) throws StorageException {
        assert (task != null) : ASSERT_FAIL_NULL_TASK;
        taskList.addTask(task);
        super.updateTaskFileStorage(taskList, taskStorage);
    }
}

package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_NULL_TASK;
import static kayu.commands.CommandMessage.MESSAGE_CREATED_EVENT;

import java.time.LocalDate;
import java.time.LocalTime;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Event;
import kayu.task.Task;

/**
 * Represents an {@link Command} that creates a {@link kayu.task.Event}
 * and saves it in {@link TaskList}.
 */
public class EventCommand extends Command {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "event";

    /**
     * Initializes an Event- {@link Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public EventCommand(String commandParams) {
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

        Task event = createTask();
        updateTasks(taskList, taskStorage, event);

        return String.format(MESSAGE_CREATED_EVENT, event, taskList.getCurrentCapacity());
    }

    private Task createTask() throws KayuException {
        String[] paramArray = getParamArray();
        String desc = CommandUtils.extractDesc(paramArray, COMMAND_WORD);
        LocalDate atDate = CommandUtils.extractDate(paramArray);
        LocalTime atTime = CommandUtils.extractTime(paramArray);

        return new Event(desc, atDate, atTime);
    }

    private String[] getParamArray() throws KayuException {
        return CommandUtils.splitUserParams(commandParams, COMMAND_WORD, Event.SPLIT_WORD);
    }

    private void updateTasks(TaskList taskList, TaskStorage taskStorage, Task task) throws StorageException {
        assert (task != null) : ASSERT_FAIL_NULL_TASK;
        taskList.addTask(task);
        super.updateTaskFileStorage(taskList, taskStorage);
    }
}

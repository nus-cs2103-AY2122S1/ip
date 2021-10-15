package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.ui.Message;

public class UpdateCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for an UpdateCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param storage Storage instance to save task info to.
     */
    public UpdateCommand(String[] args, TaskList taskList, Storage storage) {
        super(args, taskList);
        this.storage = storage;
    }

    @Override
    public String getResponse()
            throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException, HelpBotDateTimeFormatException,
            HelpBotIoException {
        Task task = taskList.updateTask(args[0], args[1]);
        storage.updateTasks();
        return Message.getUpdateMessage(task);
    }
}

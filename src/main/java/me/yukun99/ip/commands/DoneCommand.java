package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.ui.Message;

public class DoneCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for an DoneCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to set task as done in.
     * @param storage Storage instance to save task info to.
     */
    public DoneCommand(String[] args, TaskList taskList, Storage storage) {
        super(args, taskList);
        this.storage = storage;
    }

    @Override
    public String getResponse() throws HelpBotInvalidTaskException, HelpBotIoException {
        Task task = taskList.getTask(args[0]);
        boolean isDone = task.isDone();
        task.setDone();
        storage.updateTasks();
        return Message.getDoneMessage(task, isDone);
    }
}

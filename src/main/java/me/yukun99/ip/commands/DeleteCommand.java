package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.tasks.Task;

public class DeleteCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for a DeleteCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param ui Ui to send feedback to.
     * @param storage Storage instance to delete task from.
     */
    public DeleteCommand(String[] args, TaskList taskList, Ui ui, Storage storage) {
        super(args, taskList, ui);
        this.storage = storage;
    }

    @Override
    public void run() throws HelpBotInvalidTaskException {
        Task deleted = taskList.deleteTask(args[0]);
        deleted.deleteMessage(ui);
        storage.updateTasks();
    }
}

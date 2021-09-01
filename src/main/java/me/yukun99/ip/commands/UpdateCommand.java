package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Task;

public class UpdateCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for an UpdateCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param ui Ui to send feedback through.
     * @param storage Storage instance to save task info to.
     */
    public UpdateCommand(String[] args, TaskList taskList, Ui ui, Storage storage) {
        super(args, taskList, ui);
        this.storage = storage;
    }

    @Override
    public void run() throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
        try {
            Task updated = taskList.updateTask(args[0], args[1]);
            ui.update(updated);
            storage.updateTasks();
        } catch (HelpBotDateTimeFormatException e) {
            ui.error(e);
        }
    }
}

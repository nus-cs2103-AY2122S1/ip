package me.yukun99.ip.commands;

import java.util.ArrayList;
import java.util.List;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.ui.Message;

public class DeleteCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for a DeleteCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param storage Storage instance to delete task from.
     */
    public DeleteCommand(String[] args, TaskList taskList, Storage storage) {
        super(args, taskList);
        this.storage = storage;
    }

    @Override
    public String getResponse() throws HelpBotInvalidTaskException, HelpBotIoException {
        List<Task> deletedTasks = new ArrayList<>();
        boolean doDeleteAll = false;
        if (args[0].equals("all")) {
            deletedTasks = taskList.deleteAll();
            doDeleteAll = true;
        }
        if (!doDeleteAll) {
            if (args.length > 1) {
                deletedTasks = taskList.deleteTasks(args);
            } else {
                deletedTasks.add(taskList.deleteTask(args[0]));
            }
        }
        storage.updateTasks();
        if (deletedTasks.size() > 1) {
            return Message.getDeleteMultipleMessage(deletedTasks, taskList);
        }
        return deletedTasks.get(0).getDeleteMessage(taskList);
    }
}

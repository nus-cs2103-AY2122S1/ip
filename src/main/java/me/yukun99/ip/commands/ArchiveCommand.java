package me.yukun99.ip.commands;

import java.util.List;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.ui.Message;

public class ArchiveCommand extends Command {
    private final Storage storage;

    /**
     * Constructor for a Command instance.
     *
     * @param args Arguments of the command sent by the user.
     * @param taskList TaskList instance from the current HelpBot instance.
     */
    public ArchiveCommand(String[] args, TaskList taskList, Storage storage) {
        super(args, taskList);
        this.storage = storage;
    }

    @Override public String getResponse() throws HelpBotIoException, HelpBotInvalidTaskException {
        if (args[0].equals("load")) {
            List<Task> loadedTasks = storage.loadArchivedTasks();
            return Message.getArchiveLoadMessage(loadedTasks, taskList);
        }
        List<Task> archivedTasks = taskList.archiveTasks(storage, args);
        return Message.getArchiveSaveMessage(archivedTasks);
    }
}

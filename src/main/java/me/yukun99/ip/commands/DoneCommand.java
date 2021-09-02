package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.tasks.Task;

public class DoneCommand extends Command {

    public DoneCommand(String[] args, TaskList taskList) {
        super(args, taskList);
    }

    @Override
    public String getResponse() throws HelpBotInvalidTaskException {
        Task task = taskList.doneTask(args[0]);
        return task.getDoneMessage();
    }
}

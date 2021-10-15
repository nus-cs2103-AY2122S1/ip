package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.ui.Message;

public class FindCommand extends Command {
    private final TaskFinder taskFinder;

    /**
     * Constructor for a FindCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to find task from.
     * @param taskFinder TaskFinder instance to find task with.
     */
    public FindCommand(String[] args, TaskList taskList, TaskFinder taskFinder) {
        super(args, taskList);
        this.taskFinder = taskFinder;
    }

    @Override
    public String getResponse() throws HelpBotIllegalArgumentException {
        if (args.length == 0) {
            throw new HelpBotIllegalArgumentException(null);
        }
        return Message.getFindMessage(args[0], taskFinder);
    }
}

package me.yukun99.ip.commands;

import me.yukun99.ip.HelpBot;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.ui.Message;

public class ExitCommand extends Command {
    private final HelpBot helpBot;

    /**
     * Constructor for an ExitCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param helpBot HelpBot instance to exit from.
     */
    public ExitCommand(String[] args, TaskList taskList, HelpBot helpBot) {
        super(args, taskList);
        this.helpBot = helpBot;
    }

    @Override
    public String getResponse() {
        helpBot.stop();
        return Message.getExitMessage();
    }
}

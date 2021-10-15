package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.ui.Message;

public class HelpCommand extends Command {
    public HelpCommand(String[] args, TaskList taskList) {
        super(args, taskList);
    }

    @Override
    public String getResponse() {
        return Message.getHelpMessage();
    }
}

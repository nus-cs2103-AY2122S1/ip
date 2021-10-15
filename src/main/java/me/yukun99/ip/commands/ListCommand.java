package me.yukun99.ip.commands;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.ui.Message;

public class ListCommand extends Command {
    public ListCommand(String[] args, TaskList taskList) {
        super(args, taskList);
    }

    @Override
    public String getResponse() throws HelpBotDateTimeFormatException {
        if (args == null) {
            return Message.getListMessage(taskList);
        }
        DateTimePair date = DateTimePair.parse(args[0]);
        return Message.getListByDateMessage(date, taskList);
    }
}

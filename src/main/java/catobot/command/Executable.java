package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

public interface Executable {
    void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;
}

package command;

import catobot.Storage;
import catobot.Ui;
import exception.BotException;
import item.TaskList;

public interface Executable {
    void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;
}

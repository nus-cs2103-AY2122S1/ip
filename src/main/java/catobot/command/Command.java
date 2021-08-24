package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

public class Command implements Executable{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        throw new InvalidCommandException();
    }

    public boolean isExit() {
        return false;
    }
}

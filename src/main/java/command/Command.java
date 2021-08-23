package command;

import catobot.Storage;
import catobot.Ui;
import exception.BotException;
import exception.InvalidCommandException;
import item.TaskList;

public class Command implements Executable{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        throw new InvalidCommandException("Meow I don't understand this command >.<");
    }

    public boolean isExit() {
        return false;
    }
}

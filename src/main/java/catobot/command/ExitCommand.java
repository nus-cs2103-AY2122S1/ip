package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            storage.write(tasks.toStringInDoc());
            ui.exit();
        } catch (IOException e) {
            ui.showError("Meow! There is an error occured when trying to save the tasks >.<");
        }
    }
    @Override
    public boolean isExit() {
        return true;
    }
}

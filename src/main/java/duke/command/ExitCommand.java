package duke.command;

import duke.exception.BadInputFormatException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public static ExitCommand of(String content) throws BadInputFormatException {
        if (content.trim().length() > 1) {
            throw new BadInputFormatException();
        }
        return new ExitCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.print("Bye. See ya l8er allig8er!", "\033[3m*shutting down......*\033[0m");
        ui.cleanup();
        storage.write(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

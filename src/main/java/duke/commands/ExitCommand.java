package duke.commands;

import duke.exceptions.BadInputFormatException;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    public static ExitCommand of(String content) throws BadInputFormatException {
        if (content.trim().length() > 1) {
            throw new BadInputFormatException();
        }
        return new ExitCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws IOException {
        ui.print("Bye. See ya l8er allig8er!", "\033[3m*shutting down......*\033[0m");
        ui.cleanup();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

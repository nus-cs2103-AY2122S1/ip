package duke.command;

import duke.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand() { }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        Ui.bidFarewell();
    }
}
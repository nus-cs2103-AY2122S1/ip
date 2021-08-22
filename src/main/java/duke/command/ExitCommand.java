package duke.command;

import duke.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return super.isExit();
    }

    @Override
    public void execute() {
        Ui.printExitMessage();
    }
}

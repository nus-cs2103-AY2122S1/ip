package duke.command;

import duke.Ui;

public class TryAgainCommand extends Command {
    private Ui ui;

    public TryAgainCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        this.ui.prettyPrinter("Try again...? ");
    }
}

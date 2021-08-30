package duke.command;


import duke.Ui;

public class ExitCommand extends Command {

    private Ui ui;

    /**
     * Command to exit Duke app.
     *
     * @param ui Ui to handle interactions.
     */
    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

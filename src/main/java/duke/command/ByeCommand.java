package duke.command;

import duke.ui.Ui;

public class ByeCommand extends Command {
    private final Ui textUi;

    /**
     * Constructor of ByeCommand class. Initialize a ByeCommand instance
     * from a given Ui.
     *
     * @param ui A user interface
     */
    public ByeCommand(Ui ui) {
        this.textUi = ui;
    }

    @Override
    public String execute() {
        return textUi.bye();
    }
}

package duke.command;

import duke.Ui;

/**
 * TryAgainCommand is a Command that encapsulates the attributes and behaviour of prompting the user to
 * try again.
 *
 * @author leezhixuan
 */
public class TryAgainCommand extends Command {
    private Ui ui;

    /**
     * Creates an instance of TryAgainCommand.
     *
     * @param ui Instance of User Interface in use.
     */
    public TryAgainCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        this.ui.printProper("Try again...? ");
    }
}

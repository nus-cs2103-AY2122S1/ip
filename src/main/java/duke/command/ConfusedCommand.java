package duke.command;

import duke.Ui;

/**
 * Confused command is a Command that encapsulates the attributes and behaviour of someone confused.
 *
 * @author leezhixuan
 */
public class ConfusedCommand extends Command {

    private Ui ui;

    /**
     * Creates an instance of ConfusedCommand.
     *
     * @param ui Instance of User Interface in used.
     */
    public ConfusedCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String execute() {
        return "I'm confused... Put in a little more effort this time?";
    }
}

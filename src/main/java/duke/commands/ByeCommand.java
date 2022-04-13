package duke.commands;

import duke.ItemList;
import duke.Ui;

/**
 * Represents a command to terminate a <code>Duke</code> instance.
 */
public class ByeCommand extends Command {
    static final String GOODBYE = "H-hope to see u again (/O/ / w/ / O/)<3";

    @Override
    public void parseLine(String line) {
    }

    @Override
    public void execute(ItemList items, Ui ui) {
        ui.println(GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

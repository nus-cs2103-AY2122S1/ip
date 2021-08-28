package duke.commands;

import duke.ItemList;
import duke.Ui;

/**
 * Represents a command to terminate a <code>Duke</code> instance.
 */
public class ByeCommand extends Command {
    static final String GOODBYE = "Bye. Hope to see you again soon!";

    @Override
    public void parseLine(String line) {
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        ui.println(GOODBYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

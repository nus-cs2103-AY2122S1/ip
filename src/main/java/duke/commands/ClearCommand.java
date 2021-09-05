package duke.commands;

import duke.ItemList;
import duke.Ui;

/**
 * Represents a clear contents of a <code>Duke</code> instance.
 */
public class ClearCommand extends Command {
    @Override
    public void parseLine(String line) {
    }

    /**
     * Clears the contents of the <code>ItemList</code> object.
     * Optionally updates the UI.
     * 
     * @param items The list itself.
     * @param Ui Ui object to update if necessary.
     */
    @Override
    public void execute(ItemList items, Ui ui) {
        items.clear();
        ui.println("cleared!!");
    }
}

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
     * @param itemList The list itself.
     * @param Ui Ui object to update if necessary.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        itemList.clear();
    }
}

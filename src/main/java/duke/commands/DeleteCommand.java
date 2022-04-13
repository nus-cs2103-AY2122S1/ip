package duke.commands;

import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;

/**
 * Represents a command to delete a specified <code>Item</code> from an
 * <code>ItemList</code>.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Reads the index from the user input line.
     * Buffers this index into the class member.
     * 
     * @param line User input line.
     */
    @Override
    public void parseLine(String line) {
        this.index = Integer.valueOf(line.substring(7)) - 1;
    }

    /**
     * Deletes the item specified by <code>index</code> in the
     * <code>ItemList</code> and updates the UI.
     * 
     * @param items List to edit.
     * @param ui UI to update.
     */
    @Override
    public void execute(ItemList items, Ui ui) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("This task got remuwuved uwu:");
        printBuffer.add("  " + items.remove(this.index).toString());
        ui.println(printBuffer);
    }
}

package duke.commands;

import java.util.ArrayList;

import duke.ItemList;
import duke.Ui;
import duke.items.Item;

/**
 * Represents a command to mark a specified <code>Item</code> from an
 * <code>ItemList</code> as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Reads the index from the user input line.
     * Buffers this index into the class member.
     * 
     * @param line User input line.
     */
    @Override
    public void parseLine(String line) {
        this.index = Integer.valueOf(line.substring(5)) - 1;
    }

    /**
     * Marks the item specified by <code>index</code> in the
     * <code>ItemList</code> as done and updates the UI.
     * 
     * @param items List to edit.
     * @param ui UI to update.
     */
    @Override
    public void execute(ItemList items, Ui ui) {
        Item x = items.get(this.index);
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("NICE OWO! Me mark dis task as donned!!:");
        x.markAsDone();
        printBuffer.add("  " + x.toString());
        ui.println(printBuffer);
    }
}

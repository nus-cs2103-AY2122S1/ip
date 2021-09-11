package duke.commands;

import duke.DukeException;
import duke.ItemList;
import duke.Ui;

import java.lang.NumberFormatException;
import java.util.ArrayList;

/**
 * Represents a command to delete a specified <code>Item</code> from an
 * <code>ItemList</code>.
 */
public class TagAddCommand extends Command {
    private int index;
    private StringBuilder tag = new StringBuilder();

    /**
     * Reads the index from the user input line.
     * Buffers this index into the class member.
     * 
     * @param line User input line.
     */
    @Override
    public void parseLine(String line) throws DukeException {
        String[] lineSegments = line.split(" ");
        if (lineSegments.length <= 2) {
            throw new DukeException("Wrong argwuments! Uwse tag [number] [tag] pwease!!");
        }
        try {
            this.index = Integer.valueOf(lineSegments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Wrong argwuments! Uwse tag [number] [tag] pwease!!");
        }
        for (int i = 2; i < lineSegments.length; i++) {
            this.tag.append(lineSegments[i] + " ");
        }
        this.tag.deleteCharAt(this.tag.length() - 1);
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
        items.get(this.index).addTag(this.tag.toString());
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("This task got tagged uwu:");
        printBuffer.add("  " + items.get(this.index).toString());
        ui.println(printBuffer);
    }
}


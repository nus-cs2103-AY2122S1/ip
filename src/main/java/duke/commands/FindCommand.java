package duke.commands;

import duke.items.Item;
import duke.DukeException;
import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Represents a command to find an <code>Item</code> object
 * matching a String query.
 */
public class FindCommand extends Command {
    private String query;

    @Override
    public void parseLine(String line) throws DukeException {
        if (line.length() <= 5) {
            throw new DukeException("Argument cannot be empty.");
        } else {
            this.query = line.substring(5);
        }
    }

    /**
    * Displays all <code>Item</code> objects
    * matching the query though the UI.
    * 
    * @param itemList Source of items.
    * @param ui UI to update.
    */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        Item currItem;
        ListIterator<Item> iterator = itemList.listIterator();
        if (!iterator.hasNext()) {
            ui.println("Empty!");
        } else {
            ArrayList<String> printBuffer = new ArrayList<>();
            printBuffer.add("Here are the matching in your list:");
            Integer currIdx = 1;
            while (iterator.hasNext()) {
                currItem = iterator.next();
                if (currItem.queryString(this.query)) {
                    printBuffer.add(currIdx.toString() + ". " + currItem.toString());
                }
                currIdx++;
            }
            ui.println(printBuffer);
        }
    }
}
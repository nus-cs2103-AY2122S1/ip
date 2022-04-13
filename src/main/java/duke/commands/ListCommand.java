package duke.commands;

import duke.items.Item;
import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Represents a command to display all <code>Item</code> objects
 * in a <code>items</code> though an UI.
 */
public class ListCommand extends Command {
    @Override
    public void parseLine(String line) {}

    /**
    * Displays all <code>Item</code> objects
    * in the <code>items</code> though the UI.
    * 
    * @param items List to print.
    * @param ui UI to update.
    */
    @Override
    public void execute(ItemList items, Ui ui) {
        ListIterator<Item> iterator = items.listIterator();
        if (!iterator.hasNext()) {
            ui.println("No tasks uwu!");
        } else {
            ArrayList<String> printBuffer = new ArrayList<>();
            printBuffer.add("Here are the tasks in your list:");
            Integer currIdx = 1;
            while (iterator.hasNext()) {
                printBuffer.add(currIdx.toString() + ". " + iterator.next().toString());
                currIdx++;
            }
            ui.println(printBuffer);
        }
    }
}

package duke.commands;

import duke.items.Item;
import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;
import java.util.ListIterator;

public class ListCommand extends Command {
    
    @Override
    public void parseLine(String line) {}

    @Override
    public void execute(ItemList itemList, Ui ui) {
        ListIterator<Item> iterator = itemList.listIterator();
        if (!iterator.hasNext()) {
            ui.println("Empty!");
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

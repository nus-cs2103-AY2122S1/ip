package duke.commands;

import duke.Duke;
import duke.Item;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListCommand extends Command {
    
    @Override
    public void parseLine(String line) {}

    @Override
    public void execute(LinkedList<Item> itemList) {
        ListIterator<Item> iterator = itemList.listIterator();
        if (!iterator.hasNext()) {
            System.out.println(Duke.styleResponse("Empty!"));
        } else {
            ArrayList<String> printBuffer = new ArrayList<>();
            printBuffer.add("Here are the tasks in your list:");
            Integer currIdx = 1;
            while (iterator.hasNext()) {
                printBuffer.add(currIdx.toString() + ". " + iterator.next().toString());
                currIdx++;
            }
            System.out.println(Duke.styleResponse(printBuffer));
        }
    }
}

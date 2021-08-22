package duke.commands;

import duke.Item;
import java.util.LinkedList;

public class ListCommand extends Command {
    
    @Override
    public void parseLine(String line) {}

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked ListCommand");
    }
}

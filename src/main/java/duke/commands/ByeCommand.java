package duke.commands;

import duke.Item;
import java.util.LinkedList;

public class ByeCommand extends Command {
    
    @Override
    public void parseLine(String line) {
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked ByeCommand");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.commands;

import duke.Item;
import java.util.LinkedList;

public class DoneCommand extends Command {

    @Override
    public void parseArg(String args) {
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked DoneCommand");
    }
}

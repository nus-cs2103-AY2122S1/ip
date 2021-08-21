package duke.commands;

import java.util.LinkedList;
import duke.Item;

abstract class Command {
    String fullCommand;

    abstract void execute(LinkedList<Item> itemList);

    abstract void parseArg(String args);

    public boolean isExit() {
        return false;
    }
}

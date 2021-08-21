package duke.commands;

import java.util.LinkedList;
import duke.Item;

abstract class Command {
    String fullCommand;

    abstract void execute(LinkedList<Item> itemList);

    abstract void parseArg(String line);

    public boolean isExit() {
        return false;
    }
}

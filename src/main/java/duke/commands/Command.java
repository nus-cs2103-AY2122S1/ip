package duke.commands;

import java.util.LinkedList;
import duke.Item;

abstract class Command {
    abstract void execute(LinkedList<Item> itemList);

    abstract void parseLine(String line);

    public boolean isExit() {
        return false;
    }
}

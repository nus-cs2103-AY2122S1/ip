package duke.commands;

import java.util.LinkedList;
import duke.Item;

public abstract class Command {
    public abstract void execute(LinkedList<Item> itemList);

    public abstract void parseLine(String line);

    public boolean isExit() {
        return false;
    }
}

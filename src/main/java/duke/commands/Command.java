package duke.commands;

import java.util.LinkedList;

import duke.DukeException;
import duke.Item;

public abstract class Command {
    public abstract void execute(LinkedList<Item> itemList);

    public abstract void parseLine(String line) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

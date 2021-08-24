package duke.commands;

import duke.ItemList;
import duke.DukeException;
import duke.Ui;

public abstract class Command {
    public abstract void execute(ItemList itemList, Ui ui) throws DukeException;

    public abstract void parseLine(String line) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

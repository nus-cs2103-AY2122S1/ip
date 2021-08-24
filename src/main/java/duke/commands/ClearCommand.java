package duke.commands;

import duke.ItemList;
import duke.Ui;

public class ClearCommand extends Command {
    static final String GOODBYE = "Bye. Hope to see you again soon!";

    @Override
    public void parseLine(String line) {
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        itemList.clear();
    }
}

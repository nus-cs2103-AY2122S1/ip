package duke.commands;

import duke.items.Item;
import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;

public class DoneCommand extends Command {
    private int index;

    @Override
    public void parseLine(String line) {
        this.index = Integer.valueOf(line.substring(5)) - 1;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        Item x = itemList.get(this.index);
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Nice! I've marked this task as done:");
        x.markAsDone();
        printBuffer.add("  " + x.toString());
        ui.println(printBuffer);
    }
}

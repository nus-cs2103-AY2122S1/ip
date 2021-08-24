package duke.commands;

import duke.ItemList;
import duke.Ui;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;

    @Override
    public void parseLine(String line) {
        this.index = Integer.valueOf(line.substring(7)) - 1;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Noted. I've removed this task:");
        printBuffer.add("  " + itemList.remove(this.index).toString());
        ui.println(printBuffer);
    }
}

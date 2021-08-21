package duke.commands;

import duke.Duke;
import duke.Item;
import java.util.ArrayList;
import java.util.LinkedList;

public class DeleteCommand extends Command {
    private int index;

    @Override
    public void parseArg(String line) {
        this.index = Integer.valueOf(line.substring(7)) - 1;
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked DeleteCommand");
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Noted. I've removed this task:");
        printBuffer.add("  " + itemList.remove(this.index).toString());
        System.out.println(Duke.styleResponse(printBuffer));
    }
}

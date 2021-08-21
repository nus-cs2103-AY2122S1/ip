package duke.commands;

import duke.Duke;
import duke.Item;
import java.util.ArrayList;
import java.util.LinkedList;

public class DoneCommand extends Command {
    private int index;

    @Override
    public void parseArg(String args) {
        this.index = Integer.valueOf(args.substring(5)) - 1;
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked DoneCommand");

        Item x = itemList.get(this.index);
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Nice! I've marked this task as done:");
        x.markAsDone();
        printBuffer.add("  " + x.toString());
        System.out.println(Duke.styleResponse(printBuffer));
    }
}

package duke.commands;

import duke.Duke;
import duke.Item;
import java.util.LinkedList;

public class ByeCommand extends Command {
    static final String GOODBYE = "Bye. Hope to see you again soon!";

    @Override
    public void parseLine(String line) {
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println(Duke.styleResponse(GOODBYE));
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.commands;

import duke.DukeException;
import duke.Item;
import java.util.LinkedList;

public class AddEventCommand extends Command {
    private String content;

    @Override
    public void parseArg(String line) {
        int idx = line.indexOf("/at");
        String name = line.substring(6, idx);
        String time = line.substring(idx + 4, line.length());
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked AddEventCommand");
        
    }
}

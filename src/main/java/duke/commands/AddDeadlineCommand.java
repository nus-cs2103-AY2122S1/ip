package duke.commands;

import duke.DukeException;
import duke.Item;
import java.util.LinkedList;

public class AddDeadlineCommand extends Command {
    private String content;

    @Override
    public void parseArg(String line) {
        int idx = line.indexOf("/by");
        String name = line.substring(9, idx);
        String time = line.substring(idx + 4, line.length());
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked AddEventCommand");
        
    }
}

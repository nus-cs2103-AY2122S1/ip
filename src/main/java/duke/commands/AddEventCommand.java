package duke.commands;

import duke.DukeException;
import duke.Event;
import duke.Item;
import java.util.LinkedList;

public class AddEventCommand extends Command {
    private String content;
    private String time;

    @Override
    public void parseLine(String line) {
        int idx = line.indexOf("/at");
        if (idx == -1) {
            throw new DukeException("Please specify /at");
        } else if (idx == line.length() - 3 || idx == line.length() - 4) {
            throw new DukeException("/at cannot be empty");
        } else if (idx == 6 || idx == 7) {
            throw new DukeException("Message cannot be empty");
        }

        this.content = line.substring(6, idx);
        this.time = line.substring(idx + 4);
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked AddEventCommand");
        itemList.add(new Event(this.content, this.time));
    }
}

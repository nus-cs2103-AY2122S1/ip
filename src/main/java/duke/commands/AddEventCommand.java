package duke.commands;

import duke.DukeException;
import duke.ItemList;
import duke.Event;
import duke.Ui;
import java.util.ArrayList;

public class AddEventCommand extends Command {
    private String content;
    private String time;

    @Override
    public void parseLine(String line) throws DukeException {
        int idx = line.indexOf("/at");
        if (idx == -1) {
            throw new DukeException("Please specify /at");
        } else if (idx == line.length() - 3 || idx == line.length() - 4) {
            throw new DukeException("/at cannot be empty");
        } else if (idx == 6 || idx == 7) {
            throw new DukeException("Message cannot be empty");
        }

        this.content = line.substring(6, idx - 1);
        this.time = line.substring(idx + 4);
    }

    @Override
    public void execute(ItemList itemList, Ui ui) throws DukeException {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        Event toAdd = new Event(this.content, this.time);
        itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", itemList.size()));
        ui.println(printBuffer);
    }
}

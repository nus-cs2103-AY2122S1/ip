package duke.commands;

import duke.Deadline;
import duke.Duke;
import duke.DukeException;
import duke.Item;
import java.util.ArrayList;
import java.util.LinkedList;

public class AddDeadlineCommand extends Command {
    private String content;
    private String time;

    @Override
    public void parseLine(String line) throws DukeException {
        int idx = line.indexOf("/by");
        if (idx == -1) {
            throw new DukeException("Please specify /by");
        } else if (idx == line.length() - 3 || idx == line.length() - 4) {
            throw new DukeException("/by cannot be empty");
        } else if (idx == 9 || idx == 10) {
            throw new DukeException("Message cannot be empty");
        }

        this.content = line.substring(9, idx - 1);
        this.time = line.substring(idx + 4);
    }

    @Override
    public void execute(LinkedList<Item> itemList) throws DukeException {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        Deadline toAdd = new Deadline(this.content, this.time);
        itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", itemList.size()));
        System.out.println(Duke.styleResponse(printBuffer));
    }
}

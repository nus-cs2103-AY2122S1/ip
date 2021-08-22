package duke.commands;

import duke.DukeException;
import duke.Deadline;
import duke.Item;
import java.util.LinkedList;

public class AddDeadlineCommand extends Command {
    private String content;
    private String time;

    @Override
    public void parseLine(String line) {
        int idx = line.indexOf("/by");
        if (idx == -1) {
            throw new DukeException("Please specify /by");
        } else if (idx == line.length() - 3 || idx == line.length() - 4) {
            throw new DukeException("/by cannot be empty");
        } else if (idx == 9 || idx == 10) {
            throw new DukeException("Message cannot be empty");
        }

        this.content = line.substring(9, idx);
        this.time = line.substring(idx + 4);
    }

    @Override
    public void execute(LinkedList<Item> itemList) {
        System.out.println("Invoked AddDeadlineCommand");
        itemList.add(new Deadline(this.content, this.time));
    }
}

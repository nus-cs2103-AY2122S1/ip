package duke.commands;

import duke.DukeException;
import duke.ItemList;
import duke.items.Deadline;
import duke.Ui;
import java.util.ArrayList;

/**
 * Represents a command to add a <code>Deadline</code> object to an
 * <code>ItemList</code>. Buffered parameters are stored in
 * <code>content</code> and <code>time</code>.
 */
public class AddDeadlineCommand extends Command {
    private String content;
    private String time;

    /**
     * Parses arguments for the object by reading user input.
     * 
     * @param line Input from the user which contains arguments.
     * @throws DukeException If arguments contain errors.
     */
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

    /**
     * Adds the <code>Deadline</code> item to the list.
     * Adds in-place, and updates the <code>Ui</code> object. The arguments
     * are taken from the class members.
     * 
     * @param itemList List to add the item to.
     * @param ui Ui to update.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) throws DukeException {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        Deadline toAdd = new Deadline(this.content, this.time);
        itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", itemList.size()));
        ui.println(printBuffer);
    }
}

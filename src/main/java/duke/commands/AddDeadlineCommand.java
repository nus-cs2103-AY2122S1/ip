package duke.commands;

import java.util.ArrayList;

import duke.DukeException;
import duke.ItemList;
import duke.Ui;
import duke.items.Deadline;

/**
 * Represents a command to add a <code>Deadline</code> object to an
 * <code>ItemList</code>. Buffered parameters are stored in
 * <code>content</code> and <code>time</code>.
 */
public class AddDeadlineCommand extends Command {
    private static final int BY_LENGTH = 3;
    private static final int COMMAND_LENGTH = 9;
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
            throw new DukeException("Pwease specify /by!!");
        } else if (idx == line.length() - BY_LENGTH || idx == line.length() - BY_LENGTH - 1) {
            throw new DukeException("/by cannot emptyyy!!");
        } else if (idx == COMMAND_LENGTH || idx == COMMAND_LENGTH + 1) {
            throw new DukeException("Message cannot be emptyyy!1!");
        }

        this.content = line.substring(COMMAND_LENGTH, idx - 1);
        this.time = line.substring(idx + BY_LENGTH + 1);
    }

    /**
     * Adds the <code>Deadline</code> item to the list.
     * Adds in-place, and updates the <code>Ui</code> object. The arguments
     * are taken from the class members.
     * 
     * @param items List to add the item to.
     * @param ui Ui to update.
     */
    @Override
    public void execute(ItemList items, Ui ui) throws DukeException {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Rawr x3 *notices task* OwO, what's this? Oo a new task:");
        Deadline toAdd = new Deadline(this.content, this.time);
        items.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now u habe %d tasks in da list. OwO", items.size()));
        ui.println(printBuffer);
    }
}

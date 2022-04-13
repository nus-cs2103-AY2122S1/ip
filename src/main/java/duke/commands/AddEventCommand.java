package duke.commands;

import duke.DukeException;
import duke.ItemList;
import duke.items.Event;
import duke.Ui;
import java.util.ArrayList;

/**
 * Represents a command to add a <code>Event</code> object to an
 * <code>ItemList</code>. Buffered parameters are stored in
 * <code>content</code> and <code>time</code>.
 */
public class AddEventCommand extends Command {
    private static final int AT_LENGTH = 3;
    private static final int COMMAND_LENGTH = 6;
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
        int idx = line.indexOf("/at");
        if (idx == -1) {
            throw new DukeException("Pwease specify /by!!");
        } else if (idx == line.length() - AT_LENGTH || idx == line.length() - AT_LENGTH - 1) {
            throw new DukeException("/by cannot emptyyy!!");
        } else if (idx == COMMAND_LENGTH || idx == COMMAND_LENGTH + 1) {
            throw new DukeException("Message cannot be emptyyy!1!");
        }

        this.content = line.substring(COMMAND_LENGTH, idx - 1);
        this.time = line.substring(idx + AT_LENGTH + 1);
    }

    /**
     * Adds the <code>Event</code> item to the list.
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
        Event toAdd = new Event(this.content, this.time);
        items.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now u habe %d tasks in da list. OwO", items.size()));
        ui.println(printBuffer);
    }
}

package duke.commands;

import duke.DukeException;
import duke.ItemList;
import duke.items.ToDo;
import duke.Ui;
import java.util.ArrayList;

/**
 * Represents a command to add a <code>ToDo</code> object to an
 * <code>ItemList</code>. Buffered parameters are stored in
 * <code>content</code>.
 */
public class AddTodoCommand extends Command {
    private static final int COMMAND_LENGTH = 4;
    private String content;

    /**
     * Parses arguments for the object by reading user input.
     * 
     * @param line Input from the user which contains arguments.
     * @throws DukeException If arguments are empty.
     */
    @Override
    public void parseLine(String line) throws DukeException {
        if (line.length() <= COMMAND_LENGTH + 1) {
            throw new DukeException("Arwgument cannawt be emptyyy!1!!");
        } else {
            this.content = line.substring(COMMAND_LENGTH + 1);
        }
    }

    /**
     * Adds the <code>ToDo</code> item to the list.
     * Adds in-place, and updates the <code>Ui</code> object. The arguments
     * are taken from the class members.
     * 
     * @param items List to add the item to.
     * @param ui Ui to update.
     */
    @Override
    public void execute(ItemList items, Ui ui) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Rawr x3 *notices task* OwO, what's this? Oo a new task:");
        ToDo toAdd = new ToDo(this.content);
        items.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now u habe %d tasks in da list. OwO)", items.size()));
        ui.println(printBuffer);
    }
}

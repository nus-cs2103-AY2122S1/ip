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
    private String content;

    /**
     * Parses arguments for the object by reading user input.
     * 
     * @param line Input from the user which contains arguments.
     * @throws DukeException If arguments are empty.
     */
    @Override
    public void parseLine(String line) throws DukeException {
        if (line.length() <= 5) {
            throw new DukeException("Argument cannot be empty.");
        } else {
            this.content = line.substring(5);
        }
    }

    /**
     * Adds the <code>ToDo</code> item to the list.
     * Adds in-place, and updates the <code>Ui</code> object. The arguments
     * are taken from the class members.
     * 
     * @param itemList List to add the item to.
     * @param ui Ui to update.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        ToDo toAdd = new ToDo(this.content);
        itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", itemList.size()));
        ui.println(printBuffer);
    }
}

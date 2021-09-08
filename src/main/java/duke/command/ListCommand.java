package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command to list all Tasks in a given TaskList.
 */
public class ListCommand extends Command {

    /**
     * The constructor for a ListCommand object.
     */
    public ListCommand() {

    }

    /**
     * Executes the Command to list all Tasks in the given TaskList.
     *
     * @param tasks The given TaskList to be referred to.
     * @param ui The given Ui to print messages to the user.
     * @param storage The given Storage to save changes to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            list += " " + (i + 1) + "." + tasks.getTask(i);
            if (i == tasks.getSize() - 1) {
                break;
            }
            list += System.lineSeparator();
        }
        ui.printTemplate(list);
    }
}

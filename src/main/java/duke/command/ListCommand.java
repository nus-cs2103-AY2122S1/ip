package duke.command;

import duke.Storage;
import duke.TaskList;

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
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {

        String list = "Here is a list of all your tasks:\n";

        for (int i = 0; i < tasks.getSize(); i++) {
            list += (i + 1) + "." + tasks.getTask(i);
            if (i == tasks.getSize() - 1) {
                break;
            }
            list += System.lineSeparator();
        }

        return list;
    }
}

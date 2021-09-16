package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;

/**
 * Command to list out the tasks.
 */
public class ListCommand extends Command {
    /**
     * The constant name to refer to this command by.
     */
    public static final String COMMAND = "LIST";

    /**
     * Default constructor.
     * 
     * @throws KatheryneException
     */
    ListCommand() throws KatheryneException {}

    /**
     * Prints out the tasks in the tasklist.
     * 
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        ui.say("Here's the list I've stored for you:");
        ui.listTasks(taskList);
    }
}

package duke.command;

import java.io.IOException;

import duke.task.TaskList;
import duke.util.AliasHandler;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'deletealias'.
 */
public class DeleteAliasCommand extends Command {

    /**
     * Creates an DeleteAliasCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public DeleteAliasCommand(String input) {
        super(input);
    }

    /**
     * Deletes an alias, as well as remove it from the config file.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return The input task list and an output message.
     * @throws IOException If an error occurs during the save operation.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        AliasHandler aliasHandler = Parser.getAliasHandler();
        AliasHandler newAliasHandler = aliasHandler.deleteAlias(ui, input);
        storage.saveAliasesToFile(newAliasHandler);
        Parser.setAliasHandler(newAliasHandler);
        String message = newAliasHandler.getRecentMessage();
        return new TaskList(taskList, message);
    }
}

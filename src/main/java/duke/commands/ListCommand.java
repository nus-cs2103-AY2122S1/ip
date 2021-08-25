package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Represents the command when the user wants to list all tasks currently saved in the bot.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Executes the command to print out all currently saved tasks.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     */
    public void execute(Storage storage, Ui ui) {
        String printedTaskList = storage.printTaskList();
        ui.printTaskListMessage(printedTaskList);
    }

    public boolean isExit() {
        return false;
    }
}

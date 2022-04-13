package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Represents the command when the user wants to list all tasks currently saved in the bot.
 */
public class ArchiveListCommand extends Command {

    public ArchiveListCommand() {
    }

    /**
     * Executes the command to print out all currently saved tasks.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     * @return Message containing all currently saved tasks.
     */
    public String execute(Storage storage, Ui ui) {
        String printedTaskList = storage.printArchiveTaskList();
        return ui.printTaskListMessage(printedTaskList);
    }
}

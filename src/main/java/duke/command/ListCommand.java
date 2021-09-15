package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the list command of Duke.
 */
public class ListCommand extends Command {

    /**
     * Factory method which generates the ListCommand from the userInput.
     *
     * @return ListCommand to be executed.
     */
    public static ListCommand generateCommand() {
        return new ListCommand();
    }

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the ListCommand.
     *
     * @param taskList TaskList of Tasks to be listed.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        ui.print(taskList);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute list command.
     *
     * @param taskList TaskList of Tasks to be listed.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        return ui.formatPrintString(taskList);
    }
}

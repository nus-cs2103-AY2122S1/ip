package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the remove archive command on duke.
 */
public class RemoveArchiveCommand extends RemoveCommand {

    /**
     * Constructor for RemoveArchiveCommand.
     *
     * @param indexToRemove index to be archived from taskList.
     */
    public RemoveArchiveCommand(int indexToRemove) {
        super(indexToRemove);
    }

    /**
     * Executes the RemoveCommand.
     *
     * @param taskList TaskList which stores the unarchived tasks.
     * @param archiveList ArchiveList to remove at indexToRemove.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (super.isRemoveAll()) {
            message = formatAndRemoveAll(archiveList);

        } else {
            message = formatAndRemoveIndexToRemove(archiveList);

        }
        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the removal of the index from the
     * archiveList.
     *
     * @param taskList TaskList which stores the unarchived tasks.
     * @param archiveList ArchiveList to remove the task at indexToRemove.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (super.isRemoveAll()) {
            message = super.formatAndRemoveAll(archiveList);

        } else {
            message = super.formatAndRemoveIndexToRemove(archiveList);

        }
        return message;
    }
}

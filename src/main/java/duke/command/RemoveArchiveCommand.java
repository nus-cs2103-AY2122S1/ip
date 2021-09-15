package duke.command;

import duke.ArchiveList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteRemoveException;
import duke.exception.InvalidCommandException;

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
     * Factory method which generates the RemoveArchiveCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the RemoveArchiveCommand.
     * @param archiveList archiveList of duke.
     * @return RemoveArchiveCommand to be executed.
     * @throws IncompleteRemoveException if insufficient values are passed in.
     * @throws InvalidCommandException if invalid command passed in.
     */
    public static RemoveArchiveCommand generateCommand(String userInput, ArchiveList archiveList)
            throws IncompleteRemoveException, InvalidCommandException {
        String[] separated = userInput.split(SPACE);

        if (!Parser.isIntegerOrAll(separated[2]) || Parser.isOutOfRange(archiveList, separated[2])) {
            throw new IncompleteRemoveException();
        } else if (Parser.isPositiveInteger(separated[2])) {
            return new RemoveArchiveCommand(Integer.valueOf(separated[2]) - 1);
        } else if (Parser.isAll(separated[2])) {
            return new RemoveArchiveCommand(ALL);
        } else {
            throw new InvalidCommandException();
        }
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

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

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

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        return message;
    }
}

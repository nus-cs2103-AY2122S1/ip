package duke.command;

import duke.ArchiveList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteDeleteException;
import duke.exception.InvalidCommandException;

/**
 * Represents the delete archive command on duke.
 */
public class DeleteArchiveCommand extends DeleteCommand {

    /**
     * Constructs a DeleteArchiveCommand object to delete Task at indexToDelete in archiveList.
     *
     * @param indexToDelete index to be archived from taskList.
     */
    public DeleteArchiveCommand(int indexToDelete) {
        super(indexToDelete);
    }

    /**
     * Generates a DeleteArchiveCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the DeleteArchiveCommand.
     * @param archiveList archiveList of duke.
     * @return DeleteArchiveCommand to be executed.
     * @throws IncompleteDeleteException if insufficient values are passed in.
     * @throws InvalidCommandException if invalid command passed in.
     */
    public static DeleteArchiveCommand generateCommand(String userInput, ArchiveList archiveList)
            throws IncompleteDeleteException, InvalidCommandException {
        String[] separated = userInput.split(SPACE);

        if (!Parser.isIntegerOrAll(separated[2]) || Parser.isOutOfRange(archiveList, separated[2])) {
            throw new IncompleteDeleteException();
        } else if (Parser.isPositiveInteger(separated[2])) {
            return new DeleteArchiveCommand(Integer.valueOf(separated[2]) - 1);
        } else if (Parser.isAll(separated[2])) {
            return new DeleteArchiveCommand(ALL);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param taskList TaskList which stores the unarchived tasks.
     * @param archiveList ArchiveList to delete at indexToDelete.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (super.isDeleteAll()) {
            message = formatAndDeleteAll(archiveList);

        } else {
            message = formatAndDeleteIndexToDelete(archiveList);

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
     * @param archiveList ArchiveList to delete the task at indexToDelete.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     * @return String representation of the things printed in the execute method.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (super.isDeleteAll()) {
            message = super.formatAndDeleteAll(archiveList);

        } else {
            message = super.formatAndDeleteIndexToDelete(archiveList);

        }

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        return message;
    }
}

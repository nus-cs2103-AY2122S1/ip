package duke.command;

import duke.ArchiveList;
import duke.DukeList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteDeleteException;
import duke.exception.InvalidCommandException;
import duke.task.Task;

/**
 * Representation for the delete command of Duke.
 */
public class DeleteCommand extends Command {

    private final int indexToDelete;
    private final boolean isDeleteAll;

    /**
     * Constructor for DeleteCommand.
     *
     * @param indexToDelete Index of TaskList to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
        this.isDeleteAll = indexToDelete == ALL;
    }

    /**
     * Factory method which generates the DeleteCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the DeleteCommand.
     * @param taskList taskList of duke.
     * @return DeleteCommand to be executed.
     * @throws IncompleteDeleteException if insufficient values are passed in.
     * @throws InvalidCommandException if invalid command passed in.
     */
    public static DeleteCommand generateCommand(String userInput, TaskList taskList)
            throws IncompleteDeleteException, InvalidCommandException {
        String[] separated = userInput.split(SPACE);

        if (Parser.isLengthLessThanTwo(separated) || !Parser.isIntegerOrAll(separated[1])
                || Parser.isOutOfRange(taskList, separated[1])) {
            throw new IncompleteDeleteException();
        } else if (Parser.isPositiveInteger(separated[1])) {
            return new DeleteCommand(Integer.valueOf(separated[1]) - 1);
        } else if (Parser.isAll(separated[1])) {
            return new DeleteCommand(ALL);
        } else {
            throw new InvalidCommandException();
        }
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
     * Executes the DeleteCommand.
     *
     * @param taskList TaskList to delete command at index indexToDelete from.
     * @param archiveList ArchiveList to add the archived tasks to.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isDeleteAll) {
            message = formatAndDeleteAll(taskList);

        } else {
            message = formatAndDeleteIndexToDelete(taskList);

        }

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the removal of the index.
     *
     * @param taskList TaskList to delete command at index indexToDelete from.
     * @param archiveList ArchiveList to add the archived tasks to.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     * @return String representation of the things printed in the execute method.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isDeleteAll) {
            message = formatAndDeleteAll(taskList);

        } else {
            message = formatAndDeleteIndexToDelete(taskList);

        }

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        return message;
    }

    /**
     * Formats and deletes all the items from dukeList.
     *
     * @param dukeList dukeList to delete all the tasks from.
     * @return Returns the formatted string to be printed to the user.
     */
    public String formatAndDeleteAll(DukeList dukeList) {
        String message = "Noted. I've removed these tasks from the " + dukeList.type() + ":\n";

        int counter = 0;
        int size = dukeList.getSize();

        for (int i = 0; i < size; i++) {
            Task toDelete = dukeList.remove(0);
            counter++;
            message += counter + "." + toDelete + "\n";
        }

        message += "Now you have 0 tasks in the " + dukeList.type() + ".";
        return message;
    }

    /**
     * Formats and deletes task at indexToDelete from dukeList.
     *
     * @param dukeList dukeList to delete task at indexToDelete.
     * @return Returns the formatted string to be printed to the user.
     */
    public String formatAndDeleteIndexToDelete(DukeList dukeList) {
        Task toDelete = dukeList.remove(this.indexToDelete);

        return "Noted. I've removed this task from the " + dukeList.type() + ":\n" + toDelete + "\nNow you have "
                + dukeList.getSize() + " tasks in the " + dukeList.type() + ".";
    }

    /**
     * Gets isDeleteAll value which checks if the DeleteCommand
     * deletes all the tasks.
     *
     * @return isDeleteAll value.
     */
    public boolean isDeleteAll() {
        return this.isDeleteAll;
    }

    /**
     * Gets the index to be deleted.
     *
     * @return Index to be deleted.
     */
    public int getIndexToDelete() {
        return this.indexToDelete;
    }
}


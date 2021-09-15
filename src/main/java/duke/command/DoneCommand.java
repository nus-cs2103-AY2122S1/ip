package duke.command;

import duke.ArchiveList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteDoneException;
import duke.exception.InvalidCommandException;

/**
 * Representation for the done command of Duke.
 */
public class DoneCommand extends Command {

    private final int indexToMarkAsDone;
    private final boolean isMarkAll;

    /**
     * Constructor for DoneCommand.
     *
     * @param indexToMarkAsDone Index of taskList to mark as done.
     */
    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
        this.isMarkAll = indexToMarkAsDone == ALL;
    }

    /**
     * Factory method which generates the DoneCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the DoneCommand.
     * @param taskList taskList of duke.
     * @return DoneCommand to be executed.
     * @throws IncompleteDoneException if insufficient values are passed in.
     * @throws InvalidCommandException if invalid command passed in.
     */
    public static DoneCommand generateCommand(String userInput, TaskList taskList)
            throws IncompleteDoneException, InvalidCommandException {
        String[] separated = userInput.split(SPACE);

        if (Parser.isLengthLessThanTwo(separated) || !Parser.isIntegerOrAll(separated[1])
                || Parser.isOutOfRange(taskList, separated[1])) {
            throw new IncompleteDoneException();
        } else if (Parser.isPositiveInteger(separated[1])) {
            int index = Integer.valueOf(separated[1]) - 1;

            assert index >= 0 && index < taskList.getSize();

            return new DoneCommand(index);
        } else if (Parser.isAll(separated[1])) {
            return new DoneCommand(ALL);
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
     * Executes the DoneCommand.
     *
     * @param taskList TaskList to mark Task at index indexToMarkAsDone as Done.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isMarkAll) {
            message = formatAndMarkAllAsDone(taskList);

        } else {
            message = formatAndMarkDoneAtIndex(taskList);

        }

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the marking of the task as done.
     *
     * @param taskList TaskList to mark Task at index indexToMarkAsDone as Done.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;
        if (this.isMarkAll) {
            message = formatAndMarkAllAsDone(taskList);

        } else {
            message = formatAndMarkDoneAtIndex(taskList);

        }

        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);

        return message;
    }

    /**
     * Formats and marks task at indexToMarkAsDone done in given taskList.
     *
     * @param taskList taskList to mark task at indexToMarkAsDone done.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndMarkDoneAtIndex(TaskList taskList) {
        taskList.markAsDone(this.indexToMarkAsDone);

        return "Nice! I've marked this task as done:\n" + "  "
                + taskList.taskToString(this.indexToMarkAsDone);
    }

    /**
     * Formats and marks all tasks as done in given taskList.
     *
     * @param taskList taskList to mark all tasks as done.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndMarkAllAsDone(TaskList taskList) {
        String message = "Noted. I've marked these tasks as done:\n";

        taskList.markAllAsDone();

        for (int i = 0; i < taskList.getSize(); i++) {
            if (i == taskList.getSize() - 1) {
                message += i + 1 + "." + taskList.taskToString(i);
            } else {
                message += i + 1 + "." + taskList.taskToString(i) + "\n";
            }
        }
        return message;
    }
}

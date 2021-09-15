package duke.command;

import duke.ArchiveList;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteArchiveException;
import duke.task.Task;

/**
 * Representation for the Archive Command of Duke.
 */
public class ArchiveCommand extends Command {

    private final int indexToArchive;
    private final boolean isArchiveAll;

    /**
     * Constructor for ArchiveCommand.
     *
     * @param indexToArchive index of task to be archived.
     */
    public ArchiveCommand(int indexToArchive) {
        this.indexToArchive = indexToArchive;
        this.isArchiveAll = indexToArchive == ALL;
    }

    /**
     * Factory method which generates the ArchiveCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the ArchiveCommand.
     * @param taskList taskList of duke.
     * @return ArchiveCommand to be executed.
     * @throws IncompleteArchiveException if insufficient values are passed in.
     */
    public static ArchiveCommand generateCommand(
            String userInput, TaskList taskList) throws IncompleteArchiveException {
        String[] separated = userInput.split(" ");

        if (separated.length == 1) {
            throw new IncompleteArchiveException();
        }

        if (Parser.isPositiveInteger(separated[1]) && !Parser.isOutOfRange(taskList, separated[1])) {
            return new ArchiveCommand(Integer.valueOf(separated[1]) - 1);
        } else if (Parser.isAll(separated[1])) {
            return new ArchiveCommand(ALL);
        } else {
            throw new IncompleteArchiveException();
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
     * Executes the Archive Command.
     *
     * @param taskList taskList which task at indexToArchive would be archived.
     * @param archiveList archiveList to store the archived task.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isArchiveAll) {
            message = formatAndArchiveAll(taskList, archiveList);

        } else {
            message = formatAndArchiveIndex(taskList, archiveList);

        }
        ui.print(message);
    }
    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the archiving of the task at
     * indexToBeArchived.
     *
     * @param taskList taskList which task at indexToArchive would be archived.
     * @param archiveList archiveList to store the archived task.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isArchiveAll) {
            message = formatAndArchiveAll(taskList, archiveList);

        } else {
            message = formatAndArchiveIndex(taskList, archiveList);

        }
        return message;
    }

    /**
     * Formats and archives task at indexToArchive in taskList.
     *
     * @param taskList taskList to archive task at indexToArchive.
     * @param archiveList archiveList to add the archived task to.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndArchiveIndex(TaskList taskList, ArchiveList archiveList) {
        Task toArchive = taskList.archive(indexToArchive, archiveList);

        return "Noted. I've archived this task:\n" + toArchive + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }

    /**
     * Formats and archives all tasks in taskList.
     *
     * @param taskList taskList to archive all tasks.
     * @param archiveList archiveList to add the archived task to.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndArchiveAll(TaskList taskList, ArchiveList archiveList) {
        String message = "Noted. I've archived these tasks:\n";

        int counter = 0;
        int size = taskList.getSize();

        for (int i = 0; i < size; i++) {
            Task toArchive = taskList.archive(0, archiveList);
            counter++;
            message += counter + "." + toArchive + "\n";
        }

        message += "Now you have 0 tasks in the list";
        return message;
    }
}

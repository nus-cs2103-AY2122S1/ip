package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for exit command of Duke.
 */
public class ExitCommand extends Command {

    /**
     * Factory method which generates the ExitCommand from the userInput.
     *
     * @return ExitCommand to be executed.
     */
    public static ExitCommand generateCommand() {
        return new ExitCommand();
    }

    /**
     * Checks if this is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the Exit Command.
     *
     * @param taskList TaskList of Tasks.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);
        ui.exit();
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute exiting of the gui.
     *
     * @param taskList TaskList of Tasks.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to get String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
        storage.saveArchive(archiveList);
        return ui.formatExitString();
    }
}

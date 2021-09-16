package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Abstract class for Commands.
 */
public abstract class Command {
    public static final String SPACE = " ";
    public static final int ALL = -1;

    /**
     * Executes the Command.
     *
     * @param taskList taskList of Duke object to store the tasks.
     * @param archiveList archiveList of Duke object to store the archived tasks.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    public abstract void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage);

    /**
     * Checks if Command is an exit object.
     *
     * @return true if Command is exit object, else false.
     */
    public abstract boolean isExit();

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute Command.
     *
     * @param taskList taskList of Duke object to store the tasks.
     * @param archiveList archiveList of Duke object to store the archived tasks.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     * @return String representation of the things printed in the execute method.
     */
    public abstract String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage);
}

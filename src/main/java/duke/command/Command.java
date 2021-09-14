package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class for Commands.
 */
public abstract class Command {
    public static final String SPACE = " ";
    public static final int ALL = -1;

    public abstract void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage);
    public abstract boolean isExit();
    public abstract String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage);
}

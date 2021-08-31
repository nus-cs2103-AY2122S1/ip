package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The ExitCommand class encapsulates information
 * and methods pertaining to "bye" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class ExitCommand extends Command {
    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * It is used to indicate that the program should terminate.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.add("Bye. Hope to see you again soon!");
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }

    /**
     * Always returns true, as this is an ExitCommand.
     *
     * @return Returns true.
     */
    public boolean isExit() {
        return true;
    }
}

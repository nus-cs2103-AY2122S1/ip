package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The HelpCommand class encapsulates information
 * and methods pertaining to "list" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class HelpCommand extends Command {
    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The HelpCommand prints the list of commands Duke offers.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.add("I heard you need help, dont worry!");
        ui.add(ui.getHelpMessage());
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}

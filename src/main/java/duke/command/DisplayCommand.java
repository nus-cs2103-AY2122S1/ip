package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DisplayCommand class encapsulates information
 * and methods pertaining to "list" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class DisplayCommand extends Command {
    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The DisplayCommand prints the contents of the taskList.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (taskList.isEmpty()) {
            ui.add("Your list of tasks is empty!");
        } else {
            ui.add("Here are the tasks in your list:");
            int counter = 1;
            for (Task task : taskList.getTasks()) {
                ui.add("" + counter + "." + task);
                counter++;
            }
        }
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}

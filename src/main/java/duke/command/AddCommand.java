package duke.command;

import java.io.IOException;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The AddCommand class encapsulates information
 * and methods pertaining to "store" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates and initalizes a new AddCommand with the given Task.
     *
     * @param task The Task to be added/stored.
     * @return A new AddCommand object.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The AddCommand adds its assigned Task to the taskList.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        taskList.add(this.task);
        ui.add("Got it. I've added this task:");
        ui.add("  " + task);
        ui.add(String.format("Now you have %d task(s) in the list.", taskList.size()));
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}

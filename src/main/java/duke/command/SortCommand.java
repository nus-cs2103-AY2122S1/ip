package duke.command;

import java.io.IOException;
import java.util.Comparator;
import java.util.Collections;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The SortCommand class encapsulates information
 * and methods pertaining to "sort" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class SortCommand extends Command {
    private class SortCriteria implements Comparator<Task> {
        @Override
        public int compare(Task t, Task u) {
            return t.getType().compareTo(u.getType());
        }
    }

    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The SortCommand will sort the taskList according to the type of task.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        Collections.sort(taskList.getTasks(), new SortCriteria());
        ui.add("I have sorted your tasks by type!");
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }
}

package command;

import java.util.ArrayList;
import java.util.Comparator;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class SortByTimeCommand extends Command {
    /**
     * A method to sort the current taskList by time.
     * When this method is executed, all the tasks in the given
     * TaskList will be sorted by their time.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the DateCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskListToSort = taskList.getTaskList();
        taskListToSort.sort(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t1.compareTo(t2);
            }
        });
        return ui.generateDukeResponse("Task list has been sorted by time.");
    }
}

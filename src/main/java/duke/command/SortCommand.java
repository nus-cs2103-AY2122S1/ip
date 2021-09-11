package duke.command;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.DateSorter;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the SortCommand in the Duke program.
 */
public class SortCommand extends Command {
    /**
     * Returns the response after sorting all Deadline tasks.
     *
     * @param tasks Tasks of the Duke program.
     * @param ui Ui of the Duke program.
     * @param storage Storage of the Duke program.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (Task currTask : tasks.getTaskList()) {
            if (currTask instanceof Deadline) {
                deadlineTasks.add((Deadline) currTask);
            }
        }
        deadlineTasks.sort(new DateSorter());
        String deadlineTasksString = "";
        for (int i = 0; i < deadlineTasks.size(); i++) {
            deadlineTasksString = i == deadlineTasks.size() - 1
                    ? deadlineTasksString + (i + 1) + "." + deadlineTasks.get(i)
                    : deadlineTasksString + (i + 1) + "." + deadlineTasks.get(i) + "\n";
        }
        String response = deadlineTasks.size() == 0
                ? "There are no Deadline tasks."
                : "Here are the sorted Deadline tasks in your list:\n" + deadlineTasksString;
        return response;
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

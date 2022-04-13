package duke;

import java.io.IOException;

/**
 * Deletes task from the TaskList.
 */
public class Delete extends Command {

    /** Index of the task to be deleted. */
    private int index;

    public Delete(int index) {
        this.index = index;
    }

    /**
     * Runs the delete command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.getTaskFromList(index);
        tasks.deleteTask(index);
        try {
            storage.writeToFile(tasks.stringifyWholeList());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        if (t.getPreExisting()) {
            return ui.showDelete(t.getDescription(), tasks);
        } else {
            return ui.showDelete(t.toString(), tasks);
        }
    }
}

package duke;

import java.io.IOException;

/**
 * Marks task as done.
*/
public class Done extends Command {

    /** Index of the task to be marked. */
    private int index;

    public Done(int index) {
        this.index = index;
    }

    /**
     * Runs the done command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public String run(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.getTaskFromList(index);
        t.markAsDone(index);
        try {
            storage.writeToFile(tasks.stringifyWholeList());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        if (t.getPreExisting()) {
            return ui.showDone(t.getDescription());
        } else {
            return ui.showDone(t.toString());
        }

    }
}

package lania.command;

import java.io.IOException;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to mark a task as done.
 */
public class CompleteCommand extends Command {

    private int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as done, saves the resulting
     * list of tasks into hard drive and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     */
    @Override
    public void execute (TaskList tasks, Storage storage, Ui ui) {
        tasks.complete(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
        ui.showCompleteMessage(tasks, index - 1);
    }
}

package lania.command;

import java.io.IOException;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to delete a task.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the given task to the list of tasks, saves the resulting
     * list of tasks into hard drive and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showRemoveMessage(tasks, tasks.remove(index));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
    }
}

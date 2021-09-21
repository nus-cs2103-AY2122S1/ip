package lania.command;

import java.io.IOException;

import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.task.Task;
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
     * Deletes the given task to the list of tasks, updates the log and
     * saves the resulting list of tasks into hard drive
     * and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the delete command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, Log log) {

        String message;
        try {
            Task deletedTask = tasks.remove(index);
            log.addLog("delete", deletedTask, index);
            message = ui.showRemoveMessage(tasks, deletedTask);
            storage.save(tasks);
        } catch (IOException e) {
            return ui.showError();
        } catch (IndexOutOfBoundsException e) {
            return ui.showUnavailableTaskMessage(index);
        }

        return message;
    }
}

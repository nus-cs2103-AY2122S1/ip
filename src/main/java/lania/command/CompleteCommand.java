package lania.command;

import java.io.IOException;

import lania.Log;
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
     * Marks the task at the given index as done, updates the logs and
     * saves the resulting list of tasks into hard drive
     * and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the done command.
     */
    @Override
    public String execute (TaskList tasks, Storage storage, Ui ui, Log log) {

        try {
            tasks.complete(index);
            log.addLog("done", (Integer) index);
            storage.save(tasks);
        } catch (IOException e) {
            return ui.showError();
        } catch (IndexOutOfBoundsException e) {
            return ui.showUnavailableTaskMessage(index);
        }
        return ui.showCompleteMessage(tasks, index - 1);
    }
}

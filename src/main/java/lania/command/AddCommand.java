package lania.command;

import java.io.IOException;

import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.task.Task;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to add a task.
 */
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the list of tasks, updates the logs and
     * saves the resulting list of tasks into hard drive
     * and displays corresponding message.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the update command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, Log log) {
        tasks.update(task);
        log.addLog("add");
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return ui.showError();
        }
        return ui.showUpdateMessage(tasks, task);
    }
}

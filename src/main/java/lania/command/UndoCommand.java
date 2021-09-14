package lania.command;

import java.io.IOException;

import javafx.util.Pair;
import lania.Log;
import lania.Storage;
import lania.Ui;
import lania.task.Task;
import lania.task.TaskList;

/**
 * The command representing the scenario where the user
 * wants to undo a previous command.
 */
public class UndoCommand extends Command {

    /**
     * Retrieves the most recent command from the command log
     * and undo the command.
     *
     * @param tasks The user's list of tasks.
     * @param storage The object dealing with loading and storing of tasks.
     * @param ui The object dealing with user interactions.
     * @param log The object dealing with user's command logs.
     * @return The message displayed by executing the done command.
     */
    @Override
    public String execute (TaskList tasks, Storage storage, Ui ui, Log log) {
        String message;
        String command = log.getRecentLog();

        // checks the type of command of the most recent command
        // and does the corresponding actions
        if (command.equals("add")) {
            System.out.println(tasks.size());
            Task deletedTask = tasks.remove(tasks.size());
            message = ui.showRemoveMessage(tasks, deletedTask);
        } else if (command.equals("delete")) {
            Pair<Task, Integer> removedTask = log.getDeletedTask();
            tasks.update(removedTask.getKey(), removedTask.getValue());
            message = ui.showUpdateMessage(tasks, removedTask.getKey());
        } else if (command.equals("undo")) {
            int markedIndex = log.getMarkedTask();
            tasks.unComplete(markedIndex);
            message = ui.showIncompleteMessage(tasks, markedIndex - 1);
        } else {
            message = command;
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }

        return message;
    }
}

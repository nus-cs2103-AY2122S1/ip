package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UndoAddTaskCommand extends Command {
    private String command;

    public UndoAddTaskCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        storage.editFileContentsForDeletion(tasks.size());
        Task currTask = tasks.get(tasks.size() - 1);
        tasks.remove(tasks.size() - 1);
        Storage.deleteLastCommand();
        return Ui.printDeleteTask(currTask, tasks.size());
    }
}

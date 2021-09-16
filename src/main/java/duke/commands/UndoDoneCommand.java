package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UndoDoneCommand extends Command {
    private String command;

    public UndoDoneCommand(String command) {
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
        String instruction = Storage.getLastCommand();
        int taskNum = Integer.parseInt(instruction.substring(5));
        System.out.println(taskNum);
        Task currTask = tasks.get(taskNum - 1);
        currTask.setUncompleted();
        storage.editFileContentsForUndoCompletion(taskNum);
        Storage.deleteLastCommand();
        return Ui.printTaskUncompleted(currTask);
    }
}

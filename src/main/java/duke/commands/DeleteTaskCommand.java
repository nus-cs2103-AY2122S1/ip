package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class DeleteTaskCommand extends Command {
    private String command;

    /**
     * Creates DeleteTaskCommand object.
     *
     * @param command command given by user.
     */
    public DeleteTaskCommand(String command) {
        assert command.startsWith("delete");
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
        String restOfCommand = "";
        boolean isNumeric;
        try {
            restOfCommand = command.substring(7);
            int temp = Integer.parseInt(restOfCommand);
            isNumeric = true;
        } catch (NumberFormatException err) {
            isNumeric = false;
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("invalidNumberFormat");
        }
        if (!isNumeric) {
            throw new DukeException("invalidNumberFormatDelete");
        }
        int taskNum = Integer.parseInt(restOfCommand) - 1;
        if (!(taskNum < tasks.size())) {
            throw new DukeException("invalidTaskNumber");
        }
        storage.editFileContentsForDeletion(taskNum + 1);
        Task currTask = tasks.get(taskNum);
        tasks.remove(taskNum);
        Storage.storeDeleted(currTask);
        storage.storeHistory(command);
        return Ui.printDeleteTask(currTask, tasks.size());
    }
}

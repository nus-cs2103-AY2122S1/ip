package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class DoneCommand extends Command {
    private String command;

    public DoneCommand(String command) {
        assert command.startsWith("done");
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
        boolean isNumeric;
        String restOfCommand = "";
        try {
            restOfCommand = command.substring(5);
            int temp = Integer.parseInt(restOfCommand);
            isNumeric = true;
        } catch (NumberFormatException err) {
            isNumeric = false;
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("invalidNumberFormat");
        }
        if (!isNumeric) {
            throw new DukeException("invalidNumberFormat");
        }
        int taskNum = Integer.parseInt(restOfCommand) - 1;
        if (!(taskNum < tasks.size())) {
            throw new DukeException("invalidTaskNumber");
        }
        Task currTask = tasks.get(taskNum);
        currTask.setCompleted();
        storage.editFileContentsForCompletion(taskNum + 1);
        storage.storeHistory(command);
        return Ui.printTaskCompleted(currTask);
    }
}

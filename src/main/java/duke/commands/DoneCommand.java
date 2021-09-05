package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

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
        boolean numeric;
        String restOfCommand = "";
        try {
            restOfCommand = command.substring(5);
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("invalidNumberFormat");
        }
        if (!numeric) {
            throw new DukeException("invalidNumberFormat");
        }
        int taskNum = Integer.parseInt(restOfCommand) - 1;
        if (!(taskNum < tasks.size())) {
            throw new DukeException("invalidTaskNumber");
        }
        Task currTask = tasks.get(taskNum);
        currTask.setCompleted();
        storage.editFileContentsForCompletion(taskNum + 1);
        return Ui.printTaskCompleted(currTask);
    }
}

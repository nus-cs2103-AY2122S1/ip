package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

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
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < tasks.size()) {
                Task currTask = tasks.get(taskNum);
                currTask.setCompleted();
                storage.editFileContentsForCompletion(taskNum + 1);
                return Ui.printTaskCompleted(currTask);
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormat");
        }
    }
}

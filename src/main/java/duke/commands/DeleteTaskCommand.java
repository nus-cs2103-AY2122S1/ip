package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteTaskCommand extends Command {
    private String command;

    public DeleteTaskCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        String restOfCommand = command.substring(7);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < tasks.size()) {
                storage.editFileContentsForDeletion(taskNum + 1);
                Task currTask = tasks.get(taskNum);
                tasks.remove(taskNum);
                return Ui.printDeleteTask(currTask, tasks.size());
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormatDelete");
        }
    }
}
package duke.command;

import duke.task.Task;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeleteCommand extends Command {
    protected int taskNumber;
    public static final String INSTRUCTION_DELETE = "delete";

    public DeleteCommand(String taskNumber) throws DukeException {
        if (taskNumber.equals("")) {
            throw new DukeException("☹ OOPS!!! The task number of delete cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(taskNumber - 1);
        storage.update(taskNumber, task, "d");
        ui.formatPrint("Noted. I've removed this task:", "  " + task.toString(), tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + INSTRUCTION_DELETE + "] - " + taskNumber;
    }
}

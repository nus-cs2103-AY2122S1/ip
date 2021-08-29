package duke.commands;

import duke.*;
import duke.tasks.Task;

public class DeleteCommand extends Command{

    private final int taskIdx;

    public DeleteCommand(String taskNum) throws DukeException {
        try {
            taskIdx = Integer.valueOf(taskNum.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'delete' must be followed by a task number");
        }
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIdx >= taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        Task task = taskList.getTask(taskIdx);
        taskList.removeTask(taskIdx);
        storage.write(taskList);
        ui.showTaskDeleted(task, taskList);
    }

}

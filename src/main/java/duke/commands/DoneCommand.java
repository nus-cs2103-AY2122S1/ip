package duke.commands;

import duke.*;
import duke.tasks.Task;

public class DoneCommand extends Command{

    private final int taskIdx;

    public DoneCommand(String taskNum) throws DukeException {
        try {
            taskIdx = Integer.valueOf(taskNum.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'done' must be followed by a task number");
        }
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIdx >= taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        Task task = taskList.getTask(taskIdx);
        task.markAsDone();
        storage.write(taskList);
        ui.showTaskDone(task, taskList);
    }

}

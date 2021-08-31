package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    public int taskIndex;
    
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskIndex);
        ui.showDelete(task, tasks);
    };


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return (taskIndex == other.taskIndex);
        }
        return false;
    }
}

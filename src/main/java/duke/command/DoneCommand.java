package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
/**
 * Contains the executables when the user uses the 'done' command.
 */
public class DoneCommand extends Command {
    private int taskNum;
    
    public DoneCommand(int taskNumber) {
        this.taskNum = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(taskNum);
        storage.save(taskList);
        ui.doneTask(taskList.getAllTasks().get(taskNum));
    }
}

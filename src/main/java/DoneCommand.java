/**
 * Contains the executables when the user uses the 'done' command.
 */
public class DoneCommand extends Command {
    private int taskNum;
    
    DoneCommand(int taskNumber) {
        this.taskNum = taskNumber;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(taskNum);
        storage.save(taskList);
        ui.doneTask(taskList.getAllTasks().get(taskNum));
    }
}

package duke;
public class DoneCommand extends Command {

    private int taskNum;
    public DoneCommand(String command, int taskNum) {
        super(command);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.get(taskNum-1);
        temp.markAsDone();
        ui.showLine();
        ui.doneTask(temp);
        ui.showLine();
        storage.editFileAll(tasks);
    }
}

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskDone = taskList.getTask(index);
        taskDone.markAsDone();
        ui.displayCompletedMessage(taskDone);
    }
}

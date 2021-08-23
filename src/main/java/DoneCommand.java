public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.setTaskDone(index);
    }
}

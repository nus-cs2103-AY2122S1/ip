public class AddCommand extends Command{
    private final Task toAdd;

    public AddCommand(Task toAdd){
        this.toAdd = toAdd;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.toAdd);
        ui.printAdd(this.toAdd, taskList.getSize());
    }
}

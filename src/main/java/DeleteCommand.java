public class DeleteCommand extends Command{
    private static final String DELETE_MSG = "The following have been deleted:";
    
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(this.index);

        String message = DELETE_MSG + "\n" + task.toString() + "\n" + tasks.getTaskCountString();

        ui.printResponse(message);

        return true;
    }
    
}

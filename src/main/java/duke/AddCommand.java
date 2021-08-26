package duke;

public class AddCommand extends Command {
    
    private boolean isExit;
    private Task task;
    
    public AddCommand(Task task) {
        this.task = task;
        this.isExit = false;
    }
    
    @Override
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        list.add(this.task);
        ui.printAddedTaskMessage(task, list);
        storage.save(list.convertToStorageString());
    }
}

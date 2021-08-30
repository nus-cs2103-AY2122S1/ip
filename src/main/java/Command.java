public abstract class Command {
    protected final TaskList taskList;
    protected final Storage storage;
    protected final Ui ui;

    public Command(TaskList taskList, Storage storage, Ui ui){
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    public abstract void runCommand() throws DukeException;
}

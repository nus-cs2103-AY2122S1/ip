package duke;

public class Command {
    TriConsumer func;
    private boolean isExit;

    public Command(TriConsumer func, boolean isExit) {
        this.func = func;
        this.isExit = isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Exception {
        func.execute(taskList, ui, storage);
    }
    public boolean isExit() {
        return isExit;
    }
}

package duke;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
    public boolean isExit() {
        return false;
    }
}

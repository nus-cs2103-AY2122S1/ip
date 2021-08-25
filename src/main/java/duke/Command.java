package duke;

public abstract class Command {
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}

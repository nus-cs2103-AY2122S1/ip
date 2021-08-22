package duke.command;

public abstract class Command {
    public abstract void execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws duke.DukeException;
    public abstract boolean isExit();
}

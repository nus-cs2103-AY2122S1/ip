package duke;

public class Command {

    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

    }
}

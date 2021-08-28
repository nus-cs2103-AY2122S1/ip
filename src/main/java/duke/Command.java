package duke;

import java.io.IOException;
abstract class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    };
}

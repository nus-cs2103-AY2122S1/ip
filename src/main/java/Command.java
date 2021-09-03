//abstract class other classes include : AddCommand, DeleteCommand, ExitCommand, ext.)

import java.io.IOException;

public abstract class Command {
    boolean isExit;

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public boolean isExitCommand() {
        return isExit;
    }
}

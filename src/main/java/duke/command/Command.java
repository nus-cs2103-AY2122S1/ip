package duke.command;


import duke.DukeException;

public abstract class Command {
    public void execute() throws DukeException {
    }

    public boolean isExit() {
        return false;
    }
}

package duke.classes.commands;

import duke.classes.exceptions.DukeException;

public abstract class Command {
    public abstract String execute() throws DukeException;
}

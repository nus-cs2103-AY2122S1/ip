package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

abstract public class Command {
    public abstract void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException;
}

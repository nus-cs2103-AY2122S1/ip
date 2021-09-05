package duke.command;

import duke.DukeList;
import duke.exception.DukeException;

public abstract class DukeCommand {

    public DukeCommand() {}

    public abstract String run(DukeList list) throws DukeException;
}

package duke.command;

import duke.DukeException;

public interface Command {
    String run() throws DukeException;
}

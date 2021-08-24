package duke.util;

import duke.exceptions.DukeException;

public interface Command {
    String execute(TaskList tasks) throws DukeException;
}
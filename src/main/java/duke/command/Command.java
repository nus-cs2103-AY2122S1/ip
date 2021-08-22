package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {
    boolean isRunning();
    void execute(TaskList t, Ui ui, Storage storage) throws DukeException;
}

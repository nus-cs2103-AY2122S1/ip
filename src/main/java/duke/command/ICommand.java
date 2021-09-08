package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public interface ICommand {
    void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    boolean isExit();
}

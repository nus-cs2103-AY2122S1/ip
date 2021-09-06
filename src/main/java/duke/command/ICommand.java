package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public interface ICommand {
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}

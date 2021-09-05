package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface ICommand {
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public interface Command {

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    boolean isExit();
}

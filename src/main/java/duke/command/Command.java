package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public interface Command {
    String execute(TaskList taskList, Ui ui) throws DukeException;
    boolean isExit();
}




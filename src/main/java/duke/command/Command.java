package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public interface Command {
    String execute(TaskList taskList, Ui ui) throws DukeException;
    boolean isExit();
}




package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;;

abstract public class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract public Boolean isExit();

}

package utils.command;

import utils.exceptions.DukeException;
import utils.storage.Storage;
import utils.task.TaskList;
import utils.ui.Ui;

abstract public class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract public boolean isExit();

}

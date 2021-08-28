package command;

import exceptions.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

abstract public class Command {

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract public boolean isExit();

}

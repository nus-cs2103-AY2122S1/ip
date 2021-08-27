package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public abstract class Command {

    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);
}
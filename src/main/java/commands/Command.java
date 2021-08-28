package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public abstract class Command {

    /**
     * Executes the command generated from user input
     * Returns true if it is a terminating command (i.e "bye"), false otherwise
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);
}
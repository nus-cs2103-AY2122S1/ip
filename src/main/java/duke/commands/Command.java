package main.java.duke.commands;
import java.io.IOException;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.TaskList;

public abstract class Command {
    /**
     * Executes the command.
     * 
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public abstract boolean isExit();
}

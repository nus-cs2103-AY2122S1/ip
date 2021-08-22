package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * This command is for making the application exit.
 */
public class DoneCommand extends Command {
    /**
     * Constructor sets the isDone field to true. This is the only place where this happens.
     */
    public DoneCommand() {
        this.isDone = true;
    }

    /**
     * Should not be called.
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     */
    public void execute(TaskList tasklist, Ui ui, Storage store){}
}

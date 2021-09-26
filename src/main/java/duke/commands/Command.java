package duke.commands;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.utils.TaskList;


/**
 * Represent a action to be executed.
 */
public abstract class Command {    

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
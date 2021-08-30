package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    protected final String commandDescription;

    Command(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, 
    Ui ui, Storage storage) throws DukeException;

}

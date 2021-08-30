package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    protected final String commandDescription;

    Command(String command_description) {
        this.commandDescription = command_description;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, 
    Ui ui, Storage storage) throws DukeException;

}

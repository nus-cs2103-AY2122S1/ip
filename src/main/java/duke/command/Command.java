package duke.command;
import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class Command {

    public Command() {
    }

    public boolean isExist() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
    }
}
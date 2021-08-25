package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class UnknownCommand extends Command {

    /**
     * Constructs a new unknown command.
     */
    public UnknownCommand() {

    }

    /**
     * Executes the unknown command.
     * 
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    };

    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Command that is being sent when the input is incorrect
 */
public class ErrorCommand extends Command implements Revertible {
    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        System.out.println("Command inputted is not a valid command!");
        return "Command inputted is not a valid command!";
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        return "Already at the initial state!!";
    }
}

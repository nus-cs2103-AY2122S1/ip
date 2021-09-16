package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;

/**
 * Class for executing the 'bye' command.
 */
public class ByeCommand extends Command {

    public ByeCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    /**
     * Executes the bye command.
     * @return Goodbye message
     * @throws DukeException Error that occurs if wrong bye command is inputted
     */
    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new IncorrectInputException("bye", "'bye'");
            }
            return ("Goodbywe, Mastwer! Seew youw soown!\n\n");
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }

    }
}

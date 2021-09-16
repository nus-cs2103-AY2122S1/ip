package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;

public class HelpCommand extends Command {

    public HelpCommand(Storage storage, TaskList taskList, String[] strParse) {
        super(storage, taskList, strParse);
    }

    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new IncorrectInputException("help", "'help'");
            }

            return ("Commandws supported:\n\n"
                    + "- bye\n- clearall\n- help\n- list\n- todo\n- event\n- deadline\n- "
                    + "delete\n- done\n- find\n\nPlease check our website at https://ruthpohrp" +
                    ".github.io/ip/ for more details!");
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}

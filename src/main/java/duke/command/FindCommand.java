package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    /**
     * Returns the command word for a find command.
     *
     * @return "find" representing a find command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Searches the user's task list for tasks matching search term.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     */
    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        String searchTerm = parser.getSearchTerm();
        TaskList filteredList = duke.getList().search(searchTerm);
        Ui.displayTasks(filteredList, filteredList.size());
    }
}

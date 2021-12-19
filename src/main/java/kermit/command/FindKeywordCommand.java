package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Response;

/**
 * Command used to filter tasks with keyword.
 */
public class FindKeywordCommand extends Command {
    private String filterText;

    /**
     * Constructs FindKeywordCommand.
     *
     * @param filterText Text used to match description
     */
    public FindKeywordCommand(String filterText) {
        this.filterText = filterText;
    }

    /**
     * Executes command and process action.
     *
     * @param taskList Instance of task list used.
     * @param response Instance of Ui used.
     * @param storage Instance of storage class used.
     * @throws KermitException if there are any errors while performing action.
     */
    public String execute(TaskList taskList, Response response, Storage storage) {
        TaskList filteredTasks = taskList.filter(filterText);
        return response.getFilteredTasks(filteredTasks);
    }

    /**
     * Returns boolean if command is the exit command.
     *
     * @return Always returns false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns syntax for find command.
     *
     * @return Syntax for how find command is used.
     */
    protected static String getSyntax() {
        return "find <keyword>";
    }
}



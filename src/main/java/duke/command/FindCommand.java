package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;



public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private String keyword;


    /**
     * Constructor for keyword
     *
     * @param keyword Keyword to search in list
     *
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find command and search for keyword in list and returns response.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @return List of matching tasks formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatFound(taskList.findKeyword(this.keyword));
    }
}

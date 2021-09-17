package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;


public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Executes list command and returns the current list.
     *
     * @param taskList Current list
     * @param rf Response Formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @return List of tasks formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatList(taskList);
    }
}

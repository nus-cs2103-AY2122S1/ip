package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The FindCommand class represents the find command by the user, to find Task in TaskList that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Class constructor that receives a keyword that is to be checked against the Task in TaskList to find matches.
     *
     * @param keyword String to be checked against description of Task in TaskList.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the find command which locates tasks that are similar to a keyword provided.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The list of Task that match the keyword.
     * @throws DukeException Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList resultList = tasks.findMatchingTask(this.keyword);
        return ui.displayListOfTasks(resultList);
    }
}

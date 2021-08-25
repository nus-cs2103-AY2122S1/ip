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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList resultList = tasks.findMatchingTask(this.keyword);
        ui.displayListOfTasks(resultList);
    }
}

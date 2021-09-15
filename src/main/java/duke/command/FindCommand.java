package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * constructor for a FindCommand
     * @param keyword keyword for the search
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;

    }

    /**
     * executes the search command on the specified tasklist
     *
     * @param taskList tasklist to search
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList) {
        ArrayList<Task> results = taskList.find(keyword);
        return Ui.showFindResults(results);
    }

    /**
     * returns the type of command
     *
     * @return find
     */
    @Override
    public String getType() {
        return "find";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            return ((FindCommand) obj).keyword.equals(keyword);
        } else {
            return false;
        }
    }

}

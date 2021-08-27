package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
import duke.tasks.taskType;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

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
    public void execute(TaskList taskList) {
        ArrayList<Task> results = taskList.find(keyword);
        Ui.showFindResults(results);
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



}

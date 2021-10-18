package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.gui.Ui;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a FindCommand object that contains a keyword.
 */

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeCommand(TaskList taskList) throws NoSuchTaskException {
        TaskList result = taskList.searchTaskByKeyword(this.keyword);

        if (result.getTaskCount() == 0) {
            return Ui.printNoneMatchMessage();
        } else {
            return Ui.displayList(result);
        }
    }
}

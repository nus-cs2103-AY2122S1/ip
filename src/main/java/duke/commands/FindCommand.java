package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException("Keyword can't be empty.");
        }
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showMatchingTasks(taskList.findMatchingTasks(keyword));
    }

}

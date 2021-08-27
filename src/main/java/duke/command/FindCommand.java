package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {

    private String keyWords;

    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findSimilarTasks(keyWords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

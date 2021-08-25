package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTask(keyWord);
        ui.showFindTask(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

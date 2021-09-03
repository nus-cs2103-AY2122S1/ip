package duke.commands;

import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyListException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class SortCommand extends Command {

    public SortCommand() {
        super("sort");
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui) throws DukeFileException, EmptyListException {
        if (taskList.getSize() == 0) {
            throw new EmptyListException();
        }
        taskList.sortList();
        taskList.safeTasks(store);
        return taskList.printTasks(ui);
    }
}

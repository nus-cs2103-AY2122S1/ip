package duke.commands;

import duke.exceptions.EmptyListException;
import duke.exceptions.TaskNotFoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a FindCommand class that extends Command
 */
public class FindCommand extends Command {

    /**
     * This is the class field of a FindCommand.
     */
    private final String keyword;

    /**
     * This is the FindCommand constructor.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui) throws EmptyListException, TaskNotFoundException {
        return taskList.printFindTasks(ui, this.keyword);
    }
}

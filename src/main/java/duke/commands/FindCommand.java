package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.EmptyListException;
import duke.exceptions.TaskNotFoundException;

/**
 * This is a FindCommand class that extends Command
 */
public class FindCommand extends Command {

    /**
     * This is the class field of a FindCommand
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
    public void execute(TaskList taskList, Storage store, Ui ui) throws EmptyListException, TaskNotFoundException {
        taskList.printFindTasks(ui, this.keyword);
    }
}

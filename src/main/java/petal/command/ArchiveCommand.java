package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.exception.InvalidInputException;

/**
 * The ArchiveCommand class implements command
 * and handles the archiving of tasks
 */
public class ArchiveCommand implements Command {

    private final String index;

    /**
     * Constructs an ArchiveCommand instance
     * @param index The index of the task to be archived
     */
    public ArchiveCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return taskList.archiveTask(index);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

}

package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

/**
 * Command that finds tasks whose description has keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Makes a FindCommand which prints tasks which contain keyword.
     *
     * @param keyword keyword to find related tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints tasks that contain keyword.
     *
     * @param tasks current list of tasks.
     * @param ui UI that interacts with user.
     * @param fileManager filemanager to manages storage.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        Tasklist relatedTasks = tasks.findRelated(this.keyword);
        ui.printRelatedTasks(relatedTasks, this.keyword);
    }

    /**
     * Checks if command is exit command.
     *
     * @return false;
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

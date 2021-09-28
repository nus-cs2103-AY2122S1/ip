package duke.command;

import java.util.ArrayList;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

/**
 * Command that finds tasks whose description have keywords.
 */
public class FindCommand extends Command {
    private ArrayList<String> keywords = new ArrayList<>();
    public static final String HELP_MESSAGE = "To find a task, type \nfind {name/tag/time of task}\nEg. find homework";

    /**
     * Makes a FindCommand which prints tasks which contain keyword.
     *
     * @param keywords keyword to find related tasks.
     */
    public FindCommand(String ...keywords) {
        for (String keyword: keywords) {
            this.keywords.add(keyword);
        }
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Prints tasks that contain keyword.
     *
     * @param tasks current list of tasks.
     * @param ui UI that interacts with user.
     * @param fileManager filemanager to manages storage.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        Tasklist relatedTasks = tasks.findRelated(this.keywords);
        return ui.printRelatedTasks(relatedTasks, this.keywords);
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

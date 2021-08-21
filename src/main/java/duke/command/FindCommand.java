package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** Represents the "find" command. */
public class FindCommand extends Command {
    /** The keyword to be matched. */
    private String keyword;

    /**
     * FindCommand constructor.
     *
     * @param keyword The keyword to be matched.
     */
    private FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * FindCommand factory method.
     *
     * @param keyword The keyword to be matched.
     * @return A FindCommand object.
     */
    public static FindCommand of(String keyword) {
        return new FindCommand(keyword);
    }

    /**
     * Prints the tasks matching the keyword.
     *
     * @param tasks The list of tasks.
     * @param ui The UI object.
     * @param storage The storage utility.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Here are the tasks containing \"" + keyword + "\":");
        ui.print(tasks.filter((task) -> task.keywordMatcher(keyword)).toStringArray());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

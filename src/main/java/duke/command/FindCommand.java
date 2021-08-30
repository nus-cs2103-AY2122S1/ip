package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

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
     * @param storage The storage utility.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return formatOutput("Here are the tasks containing \"" + keyword + "\":",
                tasks.filter((task) -> task.matchKeyword(keyword)).toStringArray());
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

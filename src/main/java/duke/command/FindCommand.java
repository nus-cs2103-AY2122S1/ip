package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    /** The command of the user */
    private String command;

    /**
     * A public constructor to initialize the command
     * to the given one.
     *
     * @param command The user input command.
     */
    public FindCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the command inputted by the user.
     *
     * @param tasks The list of tasks stored so far.
     * @param ui The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @return The string indicating the command has been executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert command.contains("find") : "find is not included";
        String keyWord = command.split(" +", 2)[1].trim();
        int count = 0;
        String matchingTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyWord)) {
                count++;
                matchingTasks += ui.getIndent() + count + ". " + tasks.get(i)
                        + System.lineSeparator();
            }
        }
        if (count == 0) {
            return ui.noSuchTask();
        } else {
            return ui.findTask() + System.lineSeparator() + matchingTasks;
        }
    }
}

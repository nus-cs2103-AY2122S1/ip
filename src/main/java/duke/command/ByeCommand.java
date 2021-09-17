package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    public static final String COMMAND = "Bye!";

    /**
     * Constructor for ByeCommand
     * Marks isExit as true so duke can escape the application.
     *
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Execute ByeCommand, returns a goodbye message from duke.
     *
     * @param taskList Current task list
     * @param rf Response formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @return Bye message String formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatBye();
    }
}

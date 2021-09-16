package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    public static final String COMMAND = "Bye!";

    /**
     * Constructor for ByeCommand
     * will mark isExit as true so duke can escape while loop
     *
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Execute ByeCommand, returns a goodbye message from duke
     *
     * @param taskList current task list
     * @param rf Response formatter
     * @param storage current storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatBye();
    }
}

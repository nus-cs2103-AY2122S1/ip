package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class HelloCommand extends Command {
    public static final String COMMAND = "hello";

    /**
     * Constructor for Hello Command
     *
     */
    public HelloCommand() {
        this.isExit = false;
    }

    /**
     * Executes Hello Command to return greetings message.
     *
     * @param taskList Current list
     * @param rf Response formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @return Hello message formatted
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatGreetings();
    }
}

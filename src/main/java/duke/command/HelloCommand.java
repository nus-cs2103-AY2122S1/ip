package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class HelloCommand extends Command {
    public static final String COMMAND = "Hello!";

    /**
     * Constructor for Hello Command
     *
     */
    public HelloCommand() {
        this.isExit = false;
    }

    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) throws IOException {
        return rf.formatGreetings();
    }
}

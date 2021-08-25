package ligma.command;

import ligma.Storage;
import ligma.TaskList;

public interface Command {
    void execute(TaskList tasks, Storage storage);

    boolean isExit();
}

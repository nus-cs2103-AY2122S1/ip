package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui) throws MalformedCommandException;

    boolean isExit();
}


package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class List extends Command {
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        tasks.display();
    }
}

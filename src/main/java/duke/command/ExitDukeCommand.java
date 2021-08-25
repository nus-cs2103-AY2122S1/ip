package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitDukeCommand extends Command {

    public ExitDukeCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExitTrue();
        ui.showClosingMessage();
    }
}

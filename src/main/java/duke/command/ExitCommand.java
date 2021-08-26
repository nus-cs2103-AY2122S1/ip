package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;


public class ExitCommand extends Command {
    String command;
    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respondToBye();
        //System.out.println("hi");;
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}

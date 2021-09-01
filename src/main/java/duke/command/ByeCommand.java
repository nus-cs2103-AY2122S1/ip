package duke.command;


import duke.TaskList;
import duke.Ui;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayGoodbyeMsg();
    }

    public boolean isExit() {
        return true;
    }
}

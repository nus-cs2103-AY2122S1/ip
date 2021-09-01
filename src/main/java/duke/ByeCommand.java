package duke;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayGoodbyeMsg();
    }

    public boolean isExit() {
        return true;
    }
}

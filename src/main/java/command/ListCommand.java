package command;
import task.*;
import duke.*;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.list();
        ui.showResponse(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

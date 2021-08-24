package command;
import task.*;
import duke.*;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String result = tasks.find(query);
        ui.showResponse(result);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package command;
import task.*;
import duke.*;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = tasks.find(query);
        String toReturn = ui.showResponse(result);

        return toReturn;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

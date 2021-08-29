package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class ExitCommand extends Command{
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> resp = new ArrayList<>();
        resp.add(ui.displayBye());

        storage.saveStorage(taskList.getTasks());

        return resp;
    }
}

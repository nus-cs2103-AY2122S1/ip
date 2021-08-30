package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(ui.bye());
        storage.saveToStorage(taskList.getTasks());
        return outputs;
    }
}